package com.example.medicinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class MedicineReminder(val name: String, val day: String, val time: String)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPrefs = UserPreferences(this)

        setContent {
            val scope = rememberCoroutineScope()
            val textSize by userPrefs.textSizeFlow.collectAsState(initial = 18f)
            val reminders = remember { mutableStateListOf<MedicineReminder>() }

            var medicineName by remember { mutableStateOf("") }
            var medicineDay by remember { mutableStateOf("") }
            var medicineTime by remember { mutableStateOf("") }

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("Recordatorio de Medicinas", fontSize = textSize.sp) })
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Tamaño del texto: ${textSize.toInt()}sp", fontWeight = FontWeight.Bold, fontSize = textSize.sp)

                    Slider(
                        value = textSize,
                        onValueChange = {
                            scope.launch {
                                userPrefs.saveTextSize(it)
                            }
                        },
                        valueRange = 14f..30f,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Agregar Recordatorio", fontWeight = FontWeight.Bold, fontSize = textSize.sp)

                    OutlinedTextField(
                        value = medicineName,
                        onValueChange = { medicineName = it },
                        label = { Text("Nombre del medicamento", fontSize = textSize.sp) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = medicineDay,
                        onValueChange = { medicineDay = it },
                        label = { Text("Día (Ej: Lunes)", fontSize = textSize.sp) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = medicineTime,
                        onValueChange = { medicineTime = it },
                        label = { Text("Hora (Ej: 08:00 AM)", fontSize = textSize.sp) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(onClick = {
                        if (medicineName.isNotBlank() && medicineDay.isNotBlank() && medicineTime.isNotBlank()) {
                            reminders.add(MedicineReminder(medicineName, medicineDay, medicineTime))
                            medicineName = ""
                            medicineDay = ""
                            medicineTime = ""
                        }
                    }) {
                        Text("Guardar Recordatorio", fontSize = textSize.sp)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Lista de Recordatorios", fontWeight = FontWeight.Bold, fontSize = textSize.sp)

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(reminders) { reminder ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("💊 ${reminder.name}", fontWeight = FontWeight.Bold, fontSize = textSize.sp)
                                    Text("📅 Día: ${reminder.day}", fontSize = textSize.sp)
                                    Text("⏰ Hora: ${reminder.time}", fontSize = textSize.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
