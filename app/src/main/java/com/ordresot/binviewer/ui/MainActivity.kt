package com.ordresot.binviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ordresot.binviewer.ui.navigation.NavigationOverlay
import com.ordresot.binviewer.ui.theme.BINViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BINViewerTheme {
                NavigationOverlay()
            }
        }
    }
}