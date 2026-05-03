package com.example.gridlogicprototipo.ui.screen.exercises

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
        listOf(Ex3Figura.CRUZ, Ex3Figura.LUNA, Ex3Figura.RAYO),
        listOf(Ex3Figura.LUNA, Ex3Figura.RAYO, Ex3Figura.CRUZ),
        listOf(Ex3Figura.RAYO, Ex3Figura.CRUZ, null),
    )

    val opciones = listOf(
        Ex3Opcion(1, Ex3Figura.DIAMANTE, "A"),
        Ex3Opcion(2, Ex3Figura.RAYO, "B"),
        Ex3Opcion(3, Ex3Figura.LUNA, "C"), // Correcta
        Ex3Opcion(4, Ex3Figura.HEXAGONO, "D"),
        Ex3Opcion(5, Ex3Figura.CRUZ, "E"),
        Ex3Opcion(6, Ex3Figura.ESTRELLA, "F"),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = verticalGradient(colors = listOf(DarkPurple, Color(0xFF1A0B3B)))),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Ejercicio 3",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
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
                                contentAlignment = Alignment.Center,
                            ) {
                                if (figura != null) {
                                    DibujarFigura3(
                                        tipoFigura = figura,
                                        modifier = Modifier.size(50.dp),
                                        color = Color(0xFF4A148C),
                                    )
                                } else {
                                    Text(
                                        text = "?",
                                        fontSize = 32.sp,
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold,
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
            modifier = Modifier.padding(vertical = 12.dp),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(opciones.size) { indice ->
                val opcion = opciones[indice]
                TarjetaOpcion3(
                    opcion = opcion,
                    estaSeleccionado = opcionSeleccionada == opcion.id,
                ) {
                    opcionSeleccionada = opcion.id
                    onNext()
                }
            }
        }
    }
}

private enum class Ex3Figura {
    CUADRADO, CIRCULO, TRIANGULO, ESTRELLA, DIAMANTE, HEXAGONO, CRUZ, LUNA, RAYO
}

private data class Ex3Opcion(
    val id: Int,
    val tipoFigura: Ex3Figura,
    val etiqueta: String,
)

@Composable
private fun DibujarFigura3(
    tipoFigura: Ex3Figura,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
) {
    Canvas(modifier = modifier) {
        val tamano = size.minDimension
        val centro = Offset(this.size.width / 2f, this.size.height / 2f)

        when (tipoFigura) {
            Ex3Figura.CUADRADO -> {
                drawRect(
                    color = color,
                    topLeft = Offset(centro.x - (tamano / 2f), centro.y - (tamano / 2f)),
                    size = Size(tamano, tamano),
                )
            }
            Ex3Figura.CIRCULO -> {
                drawCircle(color = color, radius = tamano / 2f, center = centro)
            }
            Ex3Figura.TRIANGULO -> {
                val ruta = Path().apply {
                    moveTo(centro.x, centro.y - (tamano / 2f))
                    lineTo(centro.x - (tamano / 2f), centro.y + (tamano / 2f))
                    lineTo(centro.x + (tamano / 2f), centro.y + (tamano / 2f))
                    close()
                }
                drawPath(ruta, color)
            }
            Ex3Figura.ESTRELLA -> {
                val ruta = Path().apply {
                    val radioExterior = tamano / 2f
                    val radioInterior = tamano / 5f
                    for (i in 0 until 10) {
                        val angulo = (((Math.PI * i) / 5.0) - (Math.PI / 2.0)).toFloat()
                        val r = if ((i % 2) == 0) radioExterior else radioInterior
                        val x = centro.x + (r * kotlin.math.cos(angulo))
                        val y = centro.y + (r * kotlin.math.sin(angulo))
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                }
                drawPath(ruta, color)
            }
            Ex3Figura.DIAMANTE -> {
                val ruta = Path().apply {
                    moveTo(centro.x, centro.y - (tamano / 2f))
                    lineTo(centro.x + (tamano / 2f), centro.y)
                    lineTo(centro.x, centro.y + (tamano / 2f))
                    lineTo(centro.x - (tamano / 2f), centro.y)
                    close()
                }
                drawPath(ruta, color)
            }
            Ex3Figura.HEXAGONO -> {
                val ruta = Path().apply {
                    for (i in 0 until 6) {
                        val angulo = (Math.PI * i / 3.0).toFloat()
                        val x = centro.x + ((tamano / 2f) * kotlin.math.cos(angulo))
                        val y = centro.y + ((tamano / 2f) * kotlin.math.sin(angulo))
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                }
                drawPath(ruta, color)
            }
            Ex3Figura.CRUZ -> {
                val anchoBarra = tamano / 3f
                drawRect(
                    color = color,
                    topLeft = Offset(centro.x - (anchoBarra / 2f), centro.y - (tamano / 2f)),
                    size = Size(anchoBarra, tamano),
                )
                drawRect(
                    color = color,
                    topLeft = Offset(centro.x - (tamano / 2f), centro.y - (anchoBarra / 2f)),
                    size = Size(tamano, anchoBarra),
                )
            }
            Ex3Figura.LUNA -> {
                drawCircle(color = color, radius = tamano / 2f, center = centro)
                drawCircle(color = Color.White, radius = tamano / 2.5f, center = Offset(centro.x + (tamano / 4f), centro.y - (tamano / 10f)))
            }
            Ex3Figura.RAYO -> {
                val ruta = Path().apply {
                    moveTo(centro.x + (tamano / 4f), centro.y - (tamano / 2f))
                    lineTo(centro.x - (tamano / 2f), centro.y + (tamano / 10f))
                    lineTo(centro.x, centro.y + (tamano / 10f))
                    lineTo(centro.x - (tamano / 4f), centro.y + (tamano / 2f))
                    lineTo(centro.x + (tamano / 2f), centro.y - (tamano / 10f))
                    lineTo(centro.x, centro.y - (tamano / 10f))
                    close()
                }
                drawPath(ruta, color)
            }
        }
    }
}

@Composable
private fun TarjetaOpcion3(
    opcion: Ex3Opcion,
    estaSeleccionado: Boolean,
    alSeleccionar: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = opcion.etiqueta, color = Color.White, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .aspectRatio(1.1f)
                .fillMaxWidth()
                .clickable { alSeleccionar() }
                .border(
                    width = if (estaSeleccionado) 3.dp else 0.dp,
                    color = if (estaSeleccionado) Color.Cyan else Color.Transparent,
                    shape = RoundedCornerShape(8.dp),
                ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                DibujarFigura3(
                    tipoFigura = opcion.tipoFigura,
                    modifier = Modifier.size(35.dp),
                    color = Color(0xFF4A148C),
                )
            }
        }
    }
}
