package com.example.gridlogicprototipo.ui.screen.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gridlogicprototipo.ui.theme.DarkPurple

@Composable
fun Exercise3Screen(onNext: () -> Unit, modifier: Modifier = Modifier) {
    var opcionSeleccionada by remember { mutableStateOf<Int?>(null) }

    // Fila 1: Cruz, Luna, Rayo
    // Fila 2: Luna, Rayo, Cruz
    // Fila 3: Rayo, Cruz, [Luna]
    val datosMatriz = listOf(
        listOf(TipoFigura.CRUZ, TipoFigura.LUNA, TipoFigura.RAYO),
        listOf(TipoFigura.LUNA, TipoFigura.RAYO, TipoFigura.CRUZ),
        listOf(TipoFigura.RAYO, TipoFigura.CRUZ, null)
    )

    val opciones = listOf(
        DatosOpcion(1, TipoFigura.DIAMANTE, "A"),
        DatosOpcion(2, TipoFigura.RAYO, "B"),
        DatosOpcion(3, TipoFigura.LUNA, "C"), // Correcta
        DatosOpcion(4, TipoFigura.HEXAGONO, "D"),
        DatosOpcion(5, TipoFigura.CRUZ, "E"),
        DatosOpcion(6, TipoFigura.ESTRELLA, "F")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = verticalGradient(colors = listOf(DarkPurple, Color(0xFF1A0B3B)))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Ejercicio 3",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                datosMatriz.forEach { fila ->
                    Row(modifier = Modifier.weight(1f)) {
                        fila.forEach { figura ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .border(0.5.dp, Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                if (figura != null) {
                                    DibujarFigura(
                                        tipoFigura = figura,
                                        modifier = Modifier.size(50.dp),
                                        color = Color(0xFF4A148C)
                                    )
                                } else {
                                    Text(
                                        text = "?",
                                        fontSize = 32.sp,
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Text(
            text = "Selecciona la pieza que falta",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(opciones.size) { indice ->
                val opcion = opciones[indice]
                TarjetaOpcion(
                    opcion = opcion,
                    estaSeleccionado = opcionSeleccionada == opcion.id,
                    alSeleccionar = { 
                        opcionSeleccionada = opcion.id
                        onNext()
                    }
                )
            }
        }
    }
}
