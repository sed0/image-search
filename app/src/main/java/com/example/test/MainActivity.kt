package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.mainscreen.MainScreen
import com.example.test.ui.mainscreen.MainScreenViewModel
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainScreenViewModel = viewModel(modelClass = MainScreenViewModel::class.java)

            TestTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen(
                        state = viewModel.state.collectAsState().value,
                        onRetryClick = { viewModel.fetchImages() }
                    )
                }
            }
        }
    }
}
