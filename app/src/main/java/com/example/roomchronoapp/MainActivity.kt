package com.example.roomchronoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomchronoapp.navigation.NavManager
import com.example.roomchronoapp.ui.theme.RoomChronoAppTheme
import com.example.roomchronoapp.viewModels.ChronosViewModel
import com.example.roomchronoapp.viewModels.CronometroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val cronometroVM: CronometroViewModel by viewModels()
        val chronosVM: ChronosViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            RoomChronoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavManager(cronometroVM, chronosVM)
                }
            }
        }
    }
}