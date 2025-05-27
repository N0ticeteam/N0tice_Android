package com.example.n0tice.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.n0tice.feature.log.LogScreen
import com.example.n0tice.feature.predict.PredictScreen
import com.example.n0tice.feature.risk.RiskScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Log.route) {
        composable(BottomNavItem.Log.route) {
            LogScreen(navController)
        }

        composable(BottomNavItem.Risk.route) {
            RiskScreen(navController)
        }

        composable(BottomNavItem.Predict.route) {
            PredictScreen(navController)
        }
    }
}