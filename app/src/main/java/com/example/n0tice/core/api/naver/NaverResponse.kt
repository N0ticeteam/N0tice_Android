package com.example.n0tice.core.api.naver

data class NaverUserResponse(
    val resultcode: String,
    val message: String,
    val response: NaverUser
)

data class NaverUser(
    val id: String,
    val email: String?,
    val name: String?,
    val nickname: String?,
    val profile_image: String?,
    val age: String?,
    val gender: String?,
    val birthday: String?
)
