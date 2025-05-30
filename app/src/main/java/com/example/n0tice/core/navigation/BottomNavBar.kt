package com.example.n0tice.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.n0tice.core.navigation.BottomNavItem.Log
import com.example.n0tice.core.navigation.BottomNavItem.Predict
import com.example.n0tice.core.navigation.BottomNavItem.Risk
import com.example.n0tice.core.ui.theme.MainGreen
import com.example.n0tice.core.ui.theme.Gray
import com.example.n0tice.core.ui.theme.preFontFamily


@Composable
fun BottomNavBar(
    navController: NavHostController
) {
    val items = listOf(
        Log,
        Risk,
        Predict
    )

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            BottomNavigationItem(
                modifier = Modifier.padding(vertical = 8.dp),
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title,
                        modifier = Modifier.size(30.dp).padding(bottom = 4.dp)
                    )
                },
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = it.label,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        ),
                    )
                },
                selected = (currentRoute == it.route),
                selectedContentColor = MainGreen,
                unselectedContentColor = Gray,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}