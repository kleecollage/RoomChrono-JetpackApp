package com.example.roomchronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomchronoapp.components.ChronCard
import com.example.roomchronoapp.components.FloatButton
import com.example.roomchronoapp.components.MainTitle
import com.example.roomchronoapp.components.formatTime
import com.example.roomchronoapp.viewModels.ChronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, chronosVM: ChronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Chrono App") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, chronosVM )
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, chronosVM: ChronosViewModel) {
    Column(
        modifier = Modifier.padding(it)
    ) {
        val chronosList by chronosVM.chronosList.collectAsState()
        LazyColumn {
            items(chronosList) { item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = { chronosVM.deleteChrono(item) }
                )
                SwipeableActionsBox(
                    // startActions = listOf(delete2),
                    endActions = listOf(delete),
                    swipeThreshold = 250.dp
                ) {
                    ChronCard(
                        titulo = item.title,
                        chrono = formatTime(tiempo = item.chronos)
                    ) { navController.navigate("EditView/${item.id}") }
                }

            }
        }
    }
}





















