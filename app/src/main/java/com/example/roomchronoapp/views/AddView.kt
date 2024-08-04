package com.example.roomchronoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomchronoapp.R
import com.example.roomchronoapp.components.CircleButton
import com.example.roomchronoapp.components.MainIconButton
import com.example.roomchronoapp.components.MainTextField
import com.example.roomchronoapp.components.MainTitle
import com.example.roomchronoapp.components.formatTime
import com.example.roomchronoapp.model.Chronos
import com.example.roomchronoapp.viewModels.ChronosViewModel
import com.example.roomchronoapp.viewModels.CronometroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroVM: CronometroViewModel, chronosVM: ChronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Add Chrono") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentAddView(it, navController, cronometroVM, chronosVM )
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    cronometroVM: CronometroViewModel,
    chronosVM: ChronosViewModel
) {

    val state = cronometroVM.state

    LaunchedEffect(state.cronometroActivo) {
        cronometroVM.chronos()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize()
    ) {
        Text(
            text = formatTime(tiempo = cronometroVM.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp
            )
        ) {
            // iniciar
           CircleButton(
               icon = painterResource(id = R.drawable.play),
               enable = !state.cronometroActivo,
           ) {
               cronometroVM.iniciar()
           }
            // pausar
            CircleButton(
                icon = painterResource(id = R.drawable.pause),
                enable = state.cronometroActivo,
            ) {
                cronometroVM.pausar()
            }
            // detener
            CircleButton(
                icon = painterResource(id = R.drawable.stop),
                enable = !state.cronometroActivo,
            ) {
                cronometroVM.detener()
            }
            // mostrar | guardar
            CircleButton(
                icon = painterResource(id = R.drawable.save),
                enable = state.showSaveButton
            ) {
                cronometroVM.showTextField()
            }
        }

        if (state.showTextField) {
            MainTextField(
                value = state.title,
                onValueChange = { cronometroVM.onValue(it) },
                label = "Title"
            )

            Button(onClick = {
                chronosVM.addChrono(
                    Chronos(
                        title = state.title,
                        chronos = cronometroVM.tiempo
                    )
                )
                cronometroVM.detener()
                navController.popBackStack()
            }) {
                Text(text = "Guardar")
            }
        }
    }
}