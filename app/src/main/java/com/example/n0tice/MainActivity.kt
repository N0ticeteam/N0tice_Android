package com.example.n0tice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.n0tice.core.ui.theme.N0ticeTheme
import com.example.n0tice.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            N0ticeTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}
