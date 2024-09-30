package com.example.agendacontactos.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agendacontactos.MainActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngresarContactos(navController: NavController, usuarios: MutableList<Usuarios>){


    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = " REGISTRO CONTACTOS ",
                color =  Color(0xFF9A67E4) ,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            TextField(
                value = nombre,
                onValueChange = { nombre = it},
                label = { Text("Ingresa tu nombre") },
                placeholder = { Text("Nombre") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color(0xFFE3F2FD)
                ),
                modifier = Modifier
                    .width(280.dp)
                    .height(70.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(12.dp)
            )

            TextField(
                value = apellido,
                onValueChange = { apellido = it},
                label = { Text("Ingresa tu apellido") },
                placeholder = { Text("Apellido") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    //focusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color(0xFFE3F2FD)
                ),

                modifier = Modifier
                    .width(280.dp)
                    .height(70.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(12.dp)
            )


            TextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Ingresa tu telefono") },
                placeholder = {
                    Text("Telefono") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    containerColor = Color(0xFFE3F2FD)
                ),
                modifier = Modifier
                    .width(280.dp)
                    .height(70.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(12.dp)
            )

            TextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Ingresa tu direccion") },
                placeholder = { Text("Direccion") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    //focusedTextColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color(0xFFE3F2FD)
                ),
                modifier = Modifier
                    .width(280.dp)
                    .height(70.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(12.dp)
            )



            // Mensaje de error
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }

            // Botón para registrar el contacto
            Button(
                onClick = {
                    // Validar que los campos no estén vacíos
                    if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                        showError = true
                        errorMessage = "Debe llenar todos los campos"
                    } else {
                        // Limpiar el mensaje de error
                        showError = false
                        // Crear los datos y agregar a la lista
                        val datos = Usuarios(nombre, apellido, direccion, telefono)
                        usuarios.add(datos)

                        // Limpiar los campos
                        nombre = ""
                        apellido = ""
                        telefono = ""
                        direccion = ""

                    }
                }
            ) {
                Text(text = "Registrar")
            }
            Button(onClick = {
                // Navegar a la pantalla de lista de contactos
                navController.navigate("listarContactos")
            }) {
                Text(text = "Ver listado")
            }


        }

    }
}


