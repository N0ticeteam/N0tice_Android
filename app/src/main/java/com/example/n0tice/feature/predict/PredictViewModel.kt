package com.example.n0tice.feature.predict

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.n0tice.core.api.n0tice.N0ticeApiService
import com.example.n0tice.core.api.n0tice.N0ticeClient
import com.example.n0tice.core.api.n0tice.dto.AccidentCase
import com.example.n0tice.core.api.n0tice.dto.AccidentCaseDetail
import com.example.n0tice.core.api.n0tice.dto.UserSituation
import com.example.n0tice.core.api.n0tice.dto.UserSituationRequest
import com.example.n0tice.feature.predict.model.KindBOption
import com.example.n0tice.feature.predict.model.KindCOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredictViewModel : ViewModel() {
    val service = N0ticeClient.getInstance().create(N0ticeApiService::class.java)

    private val _userSituationState = MutableStateFlow(UserSituationState())
    var userSituationState: StateFlow<UserSituationState> = _userSituationState

    private val _accidentCaseList = MutableStateFlow<List<AccidentCase>>(emptyList())
    var accidentCaseList: StateFlow<List<AccidentCase>> = _accidentCaseList

    private val _accidentCaseDetail = MutableStateFlow<AccidentCaseDetail?>(null)
    var accidentCaseDetail: StateFlow<AccidentCaseDetail?> = _accidentCaseDetail

    fun inputUserSituation(userId: String, kindB: KindBOption, kindC: KindCOption?) {
        Log.d(
            "PredictViewModel",
            "inputUserSituation called: $userId, ${kindB.type}, ${kindC?.type}"
        )

        viewModelScope.launch {
            try {
                val response = service.setUserSituation(
                    UserSituationRequest(
                        userId,
                        kindB.type,
                        kindC?.type
                    )
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _userSituationState.value =
                            UserSituationState(
                                isSuccess = body.isSuccess,
                                message = body.message,
                                data = body.data
                            )

                        Log.d(
                            "PredictViewModel",
                            "inputUserSituation success: ${_userSituationState.value.data}"
                        )

                        _userSituationState.value.data?.id.let { id ->
                            if (id != null) {
                                getAccidentCases(id)
                            }
                        }
                    } else {
                        _userSituationState.value = UserSituationState(isSuccess = false)

                        val errorBody = response.errorBody()?.string()
                        Log.e("LogViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }
                } else {
                    _userSituationState.value = UserSituationState(isSuccess = false)

                    val errorBody = response.errorBody()?.string()
                    Log.e("PredictViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _userSituationState.value = UserSituationState(isSuccess = false)
                Log.e("PredictViewModel", "inputUserSituation Error: ${e.cause}, ${e.message}")
            }
        }
    }

    private fun getAccidentCases(inputId: Int) {
        Log.d("PredictViewModel", "getAccidentCases called: $inputId")

        viewModelScope.launch {
            try {
                val response = service.getAccidentCases(inputId)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _accidentCaseList.value = body.data

                        Log.d("PredictViewModel", "Accident Case List: ${_accidentCaseList.value}")

                    } else {
                        _accidentCaseList.value = emptyList()

                        val errorBody = response.errorBody()?.string()
                        Log.e("PredictViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }
                } else {
                    _accidentCaseList.value = emptyList()

                    val errorBody = response.errorBody()?.string()
                    Log.e("PredictViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _accidentCaseList.value = emptyList()

                Log.e("PredictViewModel", "getAccidentCases Error: ${e.message}")
            }
        }
    }

    fun getAccidentCaseDetail(inputId: Int, caseNumber: String) {
        Log.d("PredictViewModel", "getAccidentCaseDetail called: $inputId")

        viewModelScope.launch {
            try {
                val response = service.getAccidentCaseDetail(inputId, caseNumber)

                if (response.isSuccessful) {

                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _accidentCaseDetail.value = body.data
                    } else {
                        _accidentCaseDetail.value = null

                        val errorBody = response.errorBody()?.string()
                        Log.e("PredictViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }
                } else {
                    _accidentCaseDetail.value = null

                    val errorBody = response.errorBody()?.string()
                    Log.e("PredictViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _accidentCaseDetail.value = null

                Log.e("PredictViewModel", "getAccidentCaseDetail Error: ${e.message}")
            }
        }
    }
}

data class UserSituationState(
    val isSuccess: Boolean? = null,
    val message: String? = null,
    val data: UserSituation? = null
)