package com.example.n0tice.feature.risk

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.n0tice.core.api.sgis.SgisApiService
import com.example.n0tice.core.api.sgis.SgisClient
import com.example.n0tice.core.auth.SgisAccessTokenManager
import com.example.n0tice.core.model.AddrStageItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RiskViewModel(sgisManager: SgisAccessTokenManager) : ViewModel() {
    private val service: SgisApiService = SgisClient.getInstance().create(SgisApiService::class.java)

    private val _addrState = MutableStateFlow(AddrState())
    val addrState: StateFlow<AddrState> = _addrState

    private val _currentStage = MutableStateFlow(AddrStage.SI)
    val currentStage: StateFlow<AddrStage> = _currentStage

    private var accessToken = sgisManager.getCurrentAccessToken()

    init {
        viewModelScope.launch {
            if (accessToken == null || !sgisManager.isAccessTokenValid()) {
                accessToken = sgisManager.fetchAndStoreNewAccessToken()
            }

            if (accessToken != null) {
                getStateAddress(accessToken = accessToken!!, cd = null)
            } else {
                Log.e("Addr", "Access Token 발급 실패로 데이터 로드 불가.")
            }
        }
    }

    fun reset() {
        _addrState.value = _addrState.value.copy(
            guList = emptyList(),
            dongList = emptyList(),
            selectedSi = null,
            selectedGu = null,
            selectedDong = null
        )

        _currentStage.value = AddrStage.SI
    }

    // 단계별로 주소를 불러온다
    fun getStateAddress(accessToken: String, cd: String?) {
        viewModelScope.launch {
            Log.d("Addr", "accessToken: $accessToken cd: $cd")

            try {
                val response = if (cd == null) {
                    service.getTopLevelAddresses(accessToken)
                } else {
                    service.getSubLevelAddresses(accessToken, cd)
                }

                _addrState.value = when {
                    cd == null -> _addrState.value.copy(
                        siList = response.result
                    )

                    cd.length == 2 -> _addrState.value.copy(
                        guList = response.result,
                        dongList = emptyList(),
                        selectedGu = null,
                        selectedDong = null
                    )

                    cd.length == 5 -> _addrState.value.copy(
                        dongList = response.result,
                        selectedDong = null
                    )

                    else -> _addrState.value.copy(isError = true, errorMsg = "잘못된 코드")
                }

                Log.d("Addr", "getStateAddress called:: ${_addrState.value}")
            } catch (e: Exception) {
                _addrState.value =
                    _addrState.value.copy(isError = true, errorMsg = e.message)
                Log.e("Addr", "Error: ${_addrState.value.errorMsg}")
            }
        }
    }

    // 시/도 선택 시
    fun selectSi(si: AddrStageItem) {
        _addrState.value = _addrState.value.copy(
            selectedSi = si,
            guList = emptyList(), // 초기화
            dongList = emptyList(),
            selectedGu = null,
            selectedDong = null
        )

        accessToken?.let { getStateAddress(it, cd = si.cd) } // 구 리스트 요청
    }

    fun selectGu(gu: AddrStageItem) {
        _addrState.value = _addrState.value.copy(
            selectedGu = gu,
            dongList = emptyList(),
            selectedDong = null
        )
        // 지연 후 단계 변경
        viewModelScope.launch {
            delay(500)
            _currentStage.value = AddrStage.GU // 구를 선택했을 때 GU 단계로 변경
        }

        accessToken?.let { getStateAddress(it, cd = gu.cd) } // 동 리스트 요청
    }

    fun selectDong(dong: AddrStageItem) {
        _addrState.value = _addrState.value.copy(
            selectedDong = dong
        )
    }

    // SI 단계로 돌아가기
    fun backToSiStage() {
        _currentStage.value = AddrStage.SI
        _addrState.value = _addrState.value.copy(
            guList = emptyList(),
            dongList = emptyList(),
            selectedGu = null,
            selectedDong = null
        )
        // 현재 선택된 시의 구 리스트를 다시 로드
        _addrState.value.selectedSi?.let { si ->
            accessToken?.let { token -> getStateAddress(token, cd = si.cd) }
        }
    }

    // GU 단계로 돌아가기
    fun backToGuStage() {
        _currentStage.value = AddrStage.GU // **단계 명확히 GU로 변경**
        _addrState.value = _addrState.value.copy(
            selectedDong = null
        )
        // 현재 선택된 시의 구 리스트를 다시 로드
        _addrState.value.selectedSi?.let { si ->
            accessToken?.let { token -> getStateAddress(token, cd = si.cd) }
        }
    }
}

data class AddrState(
    val siList: List<AddrStageItem> = emptyList(),
    val guList: List<AddrStageItem> = emptyList(),
    val dongList: List<AddrStageItem> = emptyList(),

    val selectedSi: AddrStageItem? = null,
    val selectedGu: AddrStageItem? = null,
    val selectedDong: AddrStageItem? = null,

    val isError: Boolean = false,
    val errorMsg: String? = null
)