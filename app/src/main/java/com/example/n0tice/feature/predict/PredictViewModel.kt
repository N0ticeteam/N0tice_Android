package com.example.n0tice.feature.predict

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.n0tice.core.api.n0tice.N0ticeApiService
import com.example.n0tice.core.api.n0tice.N0ticeClient
import com.example.n0tice.core.api.n0tice.dto.UserSituationRequest
import com.example.n0tice.feature.predict.model.KindBOption
import com.example.n0tice.feature.predict.model.KindCOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredictViewModel : ViewModel() {
    val service = N0ticeClient.getInstance().create(N0ticeApiService::class.java)

    private val _userSituationState = MutableStateFlow(UserSituationState())
    val userSituationState: StateFlow<UserSituationState> = _userSituationState

    fun inputUserSituation(userId: Int, kindB: KindBOption, kindC: KindCOption?) {
        Log.d("PredictViewModel", "inputUserSituation called: $userId, ${kindB.type}, ${kindC?.type}")

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
                            UserSituationState(isSuccess = body.isSuccess, message = body.message)
                        Log.d("PredictViewModel", "inputUserSituation success: ${_userSituationState.value.message}")

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
}


data class UserSituationState(
    val isSuccess: Boolean? = null,
    val message: String? = null
)