package com.example.n0tice.feature.predict.model

enum class KindBOption(
    val label: String,
    val type: String
) {
    ETC("기타", "기타"),
    INSURANCE_COLLECTION("보험급여 징수 등", "보험급여징수등"),
    INSURANCE_FEE("보험료", "보험료"),
    WORK_ACCIDENT("업무상 사고", "업무상사고"),
    TREATMENT("요양", "요양"),
    SURVIVOR("유족", "유족"),
    DISABILITY("장해", "장해"),
    RETREATMENT("재요양", "재요양"),
    RETREATMENT_ETC("재요양 등", "재요양등"),
    COMPENSATION("체당금등", "체당금등"),
    POST_RETIRE_DIAGNOSIS("퇴직 후 진단", "퇴직후진단"),
    AVERAGE_WAGE("평균 임금", "평균임금"),
    LEAVE("휴업", "휴업");
}
