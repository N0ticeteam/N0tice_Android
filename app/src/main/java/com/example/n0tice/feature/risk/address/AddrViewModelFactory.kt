package com.example.n0tice.feature.risk.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.n0tice.core.auth.SgisAccessTokenManager

/**
 * RiskViewModel을 생성하기 위한 팩토리 클래스
 * ViewModel이 생성자 파라미터로 SgisApiService와 SgisAccessTokenManager를 필요로 할 때 사용
 */
class AddrViewModelFactory(
    private val sgisAccessTokenManager: SgisAccessTokenManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddrViewModel::class.java)) {
            // RiskViewModel의 새 인스턴스를 생성하고 필요한 종속성(dependencies)을 주입
            return AddrViewModel(sgisAccessTokenManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}