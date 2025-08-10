package com.example.n0tice.feature.predict.model

object KindBMapping {
    fun fromSituation(situation: Situation): List<KindBOption> {
        return when (situation) {
            Situation.WORK_ACCIDENT -> listOf(
                KindBOption.WORK_ACCIDENT,
                KindBOption.TREATMENT,
                KindBOption.LEAVE,
                KindBOption.RETREATMENT,
                KindBOption.DISABILITY,
                KindBOption.SURVIVOR,
                KindBOption.ETC
            )
            Situation.WORK_DISEASE -> listOf(
                KindBOption.TREATMENT,
                KindBOption.RETREATMENT,
                KindBOption.DISABILITY,
                KindBOption.SURVIVOR,
                KindBOption.WORK_ACCIDENT,
                KindBOption.ETC
            )
            Situation.COMMUTE_ACCIDENT -> listOf(
                KindBOption.INSURANCE_FEE,
                KindBOption.TREATMENT,
                KindBOption.ETC
            )
            Situation.POST_RETIRE -> listOf(
                KindBOption.POST_RETIRE_DIAGNOSIS,
                KindBOption.TREATMENT,
                KindBOption.ETC
            )
            Situation.MONEY_ISSUE -> listOf(
                KindBOption.INSURANCE_COLLECTION,
                KindBOption.INSURANCE_FEE,
                KindBOption.COMPENSATION,
                KindBOption.AVERAGE_WAGE,
                KindBOption.ETC
            )
            Situation.UNKNOWN -> KindBOption.values().toList() // 전체 표시
        }
    }
}
