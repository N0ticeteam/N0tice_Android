package com.example.n0tice.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.n0tice.core.auth.SgisAccessTokenManager
import com.example.n0tice.feature.log.LogScreen
import com.example.n0tice.feature.predict.PredictScreen
import com.example.n0tice.feature.risk.AddrSearchScreen
import com.example.n0tice.feature.risk.RiskScreen
import com.example.n0tice.feature.risk.RiskViewModel
import com.example.n0tice.feature.risk.RiskViewModelFactory

@Composable
fun NavGraph(
    navController: NavHostController,
    sgisManager: SgisAccessTokenManager
) {
    val riskViewModel: RiskViewModel = viewModel(
        factory = RiskViewModelFactory(
            sgisAccessTokenManager = sgisManager
        )
    )

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

        composable("addr_search") {
            AddrSearchScreen(
                viewModel = riskViewModel, onBackPressed = navController::popBackStack
            )
        }
    }
}
