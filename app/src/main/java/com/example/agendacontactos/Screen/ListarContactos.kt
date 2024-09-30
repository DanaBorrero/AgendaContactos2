import androidx.compose.foundation.background
import androidx.compose.foundation. layout. Arrangement
import androidx.compose.foundation. layout. Column
import androidx.compose.foundation. layout. Row
import androidx.compose.foundation. layout. fillMaxSize
import androidx.compose.foundation. layout. fillMaxWidth
import androidx.compose.foundation. layout. padding
import androidx.compose.foundation. lazy. LazyColumn
import androidx.compose.foundation. lazy. items
import androidx.compose.material. icons. Icons
import androidx.compose.material. icons. filled. Delete
import androidx.compose.material. icons. filled. Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3. Button
import androidx.compose.material3. Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3. ExperimentalMaterial3Api
import androidx.compose.material3. Icon
import androidx.compose.material3. IconButton
import androidx.compose.material3. Text
import androidx.compose.material3. TextField
import androidx.compose.material3. TextFieldDefaults
import androidx.compose.runtime. Composable
import androidx.compose.runtime. getValue
import androidx.compose.runtime. mutableStateOf
import androidx.compose.runtime. remember
import androidx.compose.runtime. setValue
import androidx.compose.ui. Modifier
import androidx.compose.ui. graphics. Color
import androidx.compose.ui. unit. dp
import androidx.navigation. NavController
import com.example.agendacontactos. Screen. Usuarios

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarContactos(navController: NavController, usuarios: MutableList<Usuarios>) {

    var showDialog by remember { mutableStateOf(false) }
    var editableUsuario by remember { mutableStateOf<Usuarios?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE3F2FD)
                    )

                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nombre: ${usuario.nombre}")
                        Text(text ="Apellido: ${usuario.apellido}")
                        Text(text = "Dirección: ${usuario.direccion}")
                        Text(text = "Teléfono: ${usuario.telefono}")
                    }
                    Row {
                        IconButton(onClick = {
                            editableUsuario = usuario
                            showDialog = true
                        }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Editar")
                        }

                        IconButton(onClick = {
                            usuarios.remove(usuario)
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }

        if (showDialog) {
            var nombre by remember { mutableStateOf(editableUsuario?.nombre ?: "") }
            var apellido by remember { mutableStateOf(editableUsuario?.apellido ?: "") }
            var direccion by remember { mutableStateOf(editableUsuario?.direccion ?: "") }
            var telefono by remember { mutableStateOf(editableUsuario?.telefono ?: "") }

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Editar usuario") },
                text = {
                    Column {
                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            )
                        )
                        TextField(
                            value = apellido,
                            onValueChange = { apellido = it },
                            label = { Text("Apellido") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            )
                        )

                        TextField(
                            value = telefono,
                            onValueChange = { telefono = it },
                            label = { Text("Telefono") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            )
                        )

                        TextField(
                            value = direccion,
                            onValueChange = { direccion = it },
                            label = { Text("Direccion") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            )
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val usuarioActualizado = editableUsuario?.copy(
                            nombre = nombre,
                            apellido = apellido,
                            direccion = direccion,
                            telefono = telefono
                        )
                        if (usuarioActualizado != null) {
                            val index = usuarios.indexOfFirst { it == editableUsuario }
                            if (index != -1) {
                                usuarios[index] = usuarioActualizado
                            }
                            editableUsuario = null
                        }
                        showDialog = false
                    }) {
                        Text("Guardar")
                    }},
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Regresar a registro")
        }
    }
}