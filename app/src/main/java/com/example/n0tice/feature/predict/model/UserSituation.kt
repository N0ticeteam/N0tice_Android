package com.example.n0tice.feature.predict.model

enum class UserSituation(
    val label: String,
    val type: String
) {
    WORK_ACCIDENT("💥 일하다가 다쳤어요", "work_accident"),
    WORK_DISEASE("🤒 일하다가 병이 생겼어요", "work_disease"),
    COMMUTE_ACCIDENT("🚗 출퇴근 중 사고가 났어요", "commute_accident"),
    POST_RETIRE("😞 퇴직한 후 병이 생겼어요", "postretire"),
    MONEY_ISSUE("💰 돈 관련 문제가 있어요", "money_issue"),
    UNKNOWN("❓ 잘 모르겠어요", "unknown");
}
