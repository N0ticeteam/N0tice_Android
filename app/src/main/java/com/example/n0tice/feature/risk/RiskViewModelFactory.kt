package com.example.n0tice.feature.risk // ViewModel이 있는 패키지와 동일하게 설정하세요

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.n0tice.core.auth.SgisAccessTokenManager

/**
 * RiskViewModel을 생성하기 위한 팩토리 클래스입니다.
 * ViewModel이 생성자 파라미터로 SgisApiService와 SgisAccessTokenManager를 필요로 할 때 사용됩니다.
 */
class RiskViewModelFactory(
    private val sgisAccessTokenManager: SgisAccessTokenManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // 컴파일러 경고를 억제합니다. 타입 캐스팅이 안전함을 보장합니다.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 요청된 ViewModel 클래스가 RiskViewModel과 동일한지 확인합니다.
        if (modelClass.isAssignableFrom(RiskViewModel::class.java)) {
            // RiskViewModel의 새 인스턴스를 생성하고 필요한 종속성(dependencies)을 주입합니다.
            return RiskViewModel(sgisAccessTokenManager) as T
        }
        // RiskViewModel이 아닌 다른 ViewModel을 요청하면 예외를 발생시킵니다.
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}