package com.example.n0tice.feature.predict.model

object KindBMapping {
    fun fromSituation(situation: UserSituation): List<KindBOption> {
        return when (situation) {
            UserSituation.WORK_ACCIDENT -> listOf(
                KindBOption.WORK_ACCIDENT,
                KindBOption.TREATMENT,
                KindBOption.LEAVE,
                KindBOption.RETREATMENT,
                KindBOption.DISABILITY,
                KindBOption.SURVIVOR,
                KindBOption.ETC
            )
            UserSituation.WORK_DISEASE -> listOf(
                KindBOption.TREATMENT,
                KindBOption.RETREATMENT,
                KindBOption.DISABILITY,
                KindBOption.SURVIVOR,
                KindBOption.WORK_ACCIDENT,
                KindBOption.ETC
            )
            UserSituation.COMMUTE_ACCIDENT -> listOf(
                KindBOption.INSURANCE_FEE,
                KindBOption.TREATMENT,
                KindBOption.ETC
            )
            UserSituation.POST_RETIRE -> listOf(
                KindBOption.POST_RETIRE_DIAGNOSIS,
                KindBOption.TREATMENT,
                KindBOption.ETC
            )
            UserSituation.MONEY_ISSUE -> listOf(
                KindBOption.INSURANCE_COLLECTION,
                KindBOption.INSURANCE_FEE,
                KindBOption.COMPENSATION,
                KindBOption.AVERAGE_WAGE,
                KindBOption.ETC
            )
            UserSituation.UNKNOWN -> KindBOption.values().toList() // 전체 표시
        }
    }
}
