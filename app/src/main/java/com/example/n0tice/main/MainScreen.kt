package com.example.n0tice.main

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.n0tice.core.navigation.BottomNavBar
import com.example.n0tice.core.navigation.BottomNavItem.Log
import com.example.n0tice.core.navigation.BottomNavItem.Predict
import com.example.n0tice.core.navigation.BottomNavItem.Risk
import com.example.n0tice.core.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    var bottomNavBar = true
    val cur = navController.currentBackStackEntryAsState().value
    cur?.destination?.route?.let { route ->
        bottomNavBar = when (route) {
            Log.route, Risk.route, Predict.route -> true
            else -> false
        }
    }

    HideSystemBars()
    Scaffold(
        bottomBar = {
            if (bottomNavBar) BottomNavBar(navController = navController)
        }
    ) {

        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavGraph(navController = navController)
        }
    }
}

@Composable
fun HideSystemBars() {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        val windowInsetsController = WindowCompat.getInsetsController(window, view)
        // 탐색 바(하단 바)만 숨기기
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}


@Composable
@Preview
fun MainPreview() {
    MainScreen()
}
