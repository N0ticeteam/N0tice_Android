package com.example.n0tice.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.n0tice.core.ui.MyIconPack
import com.example.n0tice.core.ui.myiconpack.Log
import com.example.n0tice.core.ui.myiconpack.Predict
import com.example.n0tice.core.ui.myiconpack.Risk

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val label: String
) {
    object Log :
        BottomNavItem(NavigationType.Log, MyIconPack.Log, NavigationType.Log, LabelType.Log)

    object Risk :
        BottomNavItem(NavigationType.Risk, MyIconPack.Risk, NavigationType.Risk, LabelType.Risk)

    object Predict :
        BottomNavItem(NavigationType.Predict, MyIconPack.Predict, NavigationType.Predict, LabelType.Predict)
}

class NavigationType {
    companion object {
        const val Log = "log"
        const val Risk = "risk"
        const val Predict = "predict"
    }
}

class LabelType {
    companion object {
        const val Log = "일지 작성"
        const val Risk = "고위험 사업장 검색"
        const val Predict = "산재 판례 매칭"
    }
}