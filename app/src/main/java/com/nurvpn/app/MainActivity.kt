
package com.nurvpn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF0B1220)
private val SurfaceDark = Color(0xFF172438)
private val Green = Color(0xFF35D6A0)
private val Muted = Color(0xFF9AADC2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NurVPNApp()
        }
    }
}

@Composable
fun NurVPNApp() {
    var connected by remember { mutableStateOf(false) }
    var country by remember { mutableStateOf("🇩🇪  Германия") }

    val countries = listOf(
        "🇩🇪  Германия",
        "🇳🇱  Нидерланды",
        "🇫🇷  Франция",
        "🇺🇸  США",
        "🇬🇧  Великобритания"
    )

    var showCountries by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Background,
            surface = SurfaceDark,
            primary = Green
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "NUR VPN",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "● SECURE",
                    color = Green,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(65.dp))

            Box(
                modifier = Modifier
                    .size(155.dp)
                    .background(
                        Green.copy(alpha = 0.10f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Shield,
                    contentDescription = "VPN Shield",
                    tint = Green,
                    modifier = Modifier.size(95.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                if (connected) "Подключено" else "Вы не подключены",
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                if (connected)
                    "Соединение активно"
                else
                    "Защитите своё соединение",
                color = Muted,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(45.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = SurfaceDark
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        "ВЫБОР СЕРВЕРА",
                        color = Muted,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            country,
                            color = Color.White,
                            fontSize = 17.sp,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = { showCountries = true }
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "Выбрать страну",
                                tint = Green
                            )
                        }
                    }
                }
            }

            DropdownMenu(
                expanded = showCountries,
                onDismissRequest = {
                    showCountries = false
                }
            ) {
                countries.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            country = item
                            showCountries = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = {
                    connected = !connected
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (connected) Color(0xFFB84B4B)
                        else Green
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    if (connected) "ОТКЛЮЧИТЬ"
                    else "ПОДКЛЮЧИТЬСЯ",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "NUR VPN • VERSION 1.0",
                color = Muted,
                fontSize = 11.sp
            )
        }
    }
}
