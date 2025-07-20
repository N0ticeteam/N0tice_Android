package com.example.n0tice.feature.predict.model

enum class KindCOption(
    val label: String,
    val type: String
) {
    ETC_TRAFFIC("교통사고 기타", "교통사고기타"),
    INSURANCE_COLLECTION("보험급여 징수 등", "보험급여징수등"),
    INSURANCE_FEE("보험료", "보험료"),
    TWIST("삐끗함", "삐끗함"),
    WORK_ACCIDENT("업무상 사고", "업무상사고"),
    WORK_DISEASE("업무상 질병", "업무상질병"),
    WORKPLACE("작업 현장", "작업현장"),
    COMPENSATION("체당금등", "체당금등"),
    POST_RETIRE_DIAGNOSIS("퇴직 후 진단", "퇴직후진단");
}
