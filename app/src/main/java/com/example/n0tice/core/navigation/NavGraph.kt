package com.example.n0tice.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.n0tice.core.auth.SgisAccessTokenManager
import com.example.n0tice.feature.log.LogScreen
import com.example.n0tice.feature.log.LogViewModel
import com.example.n0tice.feature.predict.LossPredictionScreen
import com.example.n0tice.feature.predict.MatchingResultScreen
import com.example.n0tice.feature.predict.PredictScreen
import com.example.n0tice.feature.predict.PredictViewModel
import com.example.n0tice.feature.predict.ResultDetailScreen
import com.example.n0tice.feature.predict.ScenarioSelectionScreen
import com.example.n0tice.feature.risk.RiskScreen
import com.example.n0tice.feature.risk.RiskViewModel
import com.example.n0tice.feature.risk.address.AddrSearchScreen
import com.example.n0tice.feature.risk.address.AddrViewModel
import com.example.n0tice.feature.risk.address.AddrViewModelFactory

@Composable
fun NavGraph(
    navController: NavHostController,
    sgisManager: SgisAccessTokenManager
) {
    val addrViewModel: AddrViewModel =
        viewModel(factory = AddrViewModelFactory(sgisAccessTokenManager = sgisManager))
    val riskViewModel: RiskViewModel = viewModel()
    val predictViewModel: PredictViewModel = viewModel()


    NavHost(navController = navController, startDestination = BottomNavItem.Log.route) {
        composable(BottomNavItem.Log.route) {
            val logViewModel: LogViewModel = viewModel()
            LogScreen(logViewModel)
        }

        composable(BottomNavItem.Risk.route) {
            RiskScreen(riskViewModel, navController)
        }

        composable(BottomNavItem.Predict.route) {
            PredictScreen(navController, predictViewModel)
        }

        composable("addr_search") {
            AddrSearchScreen(
                riskViewModel = riskViewModel,
                addrViewModel = addrViewModel,
                onBackPressed = navController::popBackStack
            )
        }

        composable("scenario") {
            ScenarioSelectionScreen(
                predictViewModel = predictViewModel,
                onBackPressed = navController::popBackStack,
                navigateToResult = { navController.navigate("matching_result") }
            )
        }

        composable("matching_result") {
            MatchingResultScreen(
                predictViewModel = predictViewModel,
                onBackPressed = navController::popBackStack,
                navigateToResult = { id: Int, caseNumber: String ->
                    navController.navigate("resultDetail/$id/$caseNumber")
                },
                navigateToLoss = { navController.navigate("loss") }
            )
        }

        composable("loss") {
            LossPredictionScreen(
                navigateToResult = { navController.navigate("matching_result") }
            )
        }

        composable(
            route = "resultDetail/{id}/{caseNumber}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("caseNumber") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val caseNumber = backStackEntry.arguments?.getString("caseNumber") ?: ""
            ResultDetailScreen(
                predictViewModel = predictViewModel,
                onBackPressed = navController::popBackStack,
                id = id,
                caseNumber = caseNumber
            )
        }
    }
}
