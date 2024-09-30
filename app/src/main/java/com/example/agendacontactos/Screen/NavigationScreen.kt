package com.example.agendacontactos.Screen

import ListarContactos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val usuarios = remember { mutableStateListOf<Usuarios>() }

    NavHost(navController = navController,
        startDestination = "ingresarContactos"
    ){
        composable("ingresarContactos") {
            IngresarContactos(navController, usuarios)
        }
        composable("listarContactos") {
            ListarContactos(navController, usuarios)
        }
    }

}