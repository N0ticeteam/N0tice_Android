package com.example.n0tice.feature.risk

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.n0tice.core.api.n0tice.N0ticeApiService
import com.example.n0tice.core.api.n0tice.N0ticeClient
import com.example.n0tice.core.api.n0tice.dto.Company
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RiskViewModel : ViewModel() {
    private val service = N0ticeClient.getInstance().create(N0ticeApiService::class.java)

    private val _companyList = MutableStateFlow<List<Company>>(emptyList())
    val companyList: StateFlow<List<Company>> = _companyList

    fun getCompanyByKeyword(keyword: String) {
        Log.d("RiskViewModel", "getCompanyByKeyword called: $keyword")

        clearCompanyList()

        viewModelScope.launch {
            val response = service.getCompanyByKeyword(keyword)

            try {
                if (response.isSuccessful) {

                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _companyList.value = body.data
                    } else {
                        _companyList.value = emptyList()

                        val errorBody = response.errorBody()?.string()
                        Log.e("RiskViewModel", "HTTP Error: ${response.code()} $errorBody")
                    }
                } else {
                    _companyList.value = emptyList()

                    val errorBody = response.errorBody()?.string()
                    Log.e("RiskViewModel", "HTTP Error: ${response.code()} $errorBody")
                }

            } catch (e: Exception) {
                _companyList.value = emptyList()
                Log.e("RiskViewModel", "getCompanyByKeyword failed: ${e.message}")
            }
        }
    }

    fun getCompanyByAddress(city: String, district: String, neighborhood: String?) {
        Log.d("RiskViewModel", "getCompanyByKeyword called: $city, $district, $neighborhood")

        viewModelScope.launch {
            val response = service.getCompanyByAddress(
                city = city,
                district = district,
                neighborhood = neighborhood ?: ""
            )

            try {
                if (response.isSuccessful) {

                    val body = response.body()
                    if (body != null && body.isSuccess) {
                        _companyList.value = body.data
                    } else {
                        _companyList.value = emptyList()
                        Log.d("RiskViewModel", "검색 결과 null: ${body?.message}")
                    }

                } else {
                    _companyList.value = emptyList()

                    val errorBody = response.errorBody()?.string()
                    Log.e("RiskViewModel", "HTTP Error: ${response.code()} $errorBody")
                }
            } catch (e: Exception) {
                _companyList.value = emptyList()
                Log.e("RiskViewModel", "getCompanyByAddress failed: ${e.message}")
            }
        }
    }

    fun clearCompanyList(){
        _companyList.value = emptyList()
    }

}
