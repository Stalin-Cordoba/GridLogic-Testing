package com.example.gridlogicprototipo.ui.screen.exercises

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class TipoFigura {
    CUADRADO, CIRCULO, TRIANGULO, ESTRELLA, DIAMANTE, HEXAGONO, CRUZ, LUNA, RAYO
}

data class DatosOpcion(
    val id: Int,
    val tipoFigura: TipoFigura,
    val etiqueta: String
)

@Composable
fun DibujarFigura(
    tipoFigura: TipoFigura,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    Canvas(modifier = modifier) {
        val tamano = size.minDimension
        val centro = Offset(this.size.width / 2, this.size.height / 2)

        when (tipoFigura) {
            TipoFigura.CUADRADO -> {
                drawRect(
                    color = color,
                    topLeft = Offset(centro.x - tamano / 2, centro.y - tamano / 2),
                    size = Size(tamano, tamano)
                )
            }
            TipoFigura.CIRCULO -> {
                drawCircle(color = color, radius = tamano / 2, center = centro)
            }
            TipoFigura.TRIANGULO -> {
                val ruta = Path().apply {
                    moveTo(centro.x, centro.y - tamano / 2)
                    lineTo(centro.x - tamano / 2, centro.y + tamano / 2)
                    lineTo(centro.x + tamano / 2, centro.y + tamano / 2)
                    close()
                }
                drawPath(ruta, color)
            }
            TipoFigura.ESTRELLA -> {
                val ruta = Path().apply {
                    val radioExterior = tamano / 2
                    val radioInterior = tamano / 5
                    for (i in 0 until 10) {
                        val angulo = Math.PI * i / 5 - Math.PI / 2
                        val r = if (i % 2 == 0) radioExterior else radioInterior
                        val x = (centro.x + r * Math.cos(angulo)).toFloat()
                        val y = (centro.y + r * Math.sin(angulo)).toFloat()
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                }
                drawPath(ruta, color)
            }
            TipoFigura.DIAMANTE -> {
                val ruta = Path().apply {
                    moveTo(centro.x, centro.y - tamano / 2)
                    lineTo(centro.x + tamano / 2, centro.y)
                    lineTo(centro.x, centro.y + tamano / 2)
                    lineTo(centro.x - tamano / 2, centro.y)
                    close()
                }
                drawPath(ruta, color)
            }
            TipoFigura.HEXAGONO -> {
                val ruta = Path().apply {
                    for (i in 0 until 6) {
                        val angulo = Math.PI * i / 3
                        val x = (centro.x + (tamano / 2) * Math.cos(angulo)).toFloat()
                        val y = (centro.y + (tamano / 2) * Math.sin(angulo)).toFloat()
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                }
                drawPath(ruta, color)
            }
            TipoFigura.CRUZ -> {
                val anchoBarra = tamano / 3
                drawRect(
                    color = color,
                    topLeft = Offset(centro.x - anchoBarra / 2, centro.y - tamano / 2),
                    size = Size(anchoBarra, tamano)
                )
                drawRect(
                    color = color,
                    topLeft = Offset(centro.x - tamano / 2, centro.y - anchoBarra / 2),
                    size = Size(tamano, anchoBarra)
                )
            }
            TipoFigura.LUNA -> {
                val ruta = Path().apply {
                    addOval(androidx.compose.ui.geometry.Rect(centro, tamano / 2))
                }
                drawCircle(color = color, radius = tamano / 2, center = centro)
                drawCircle(color = Color.White, radius = tamano / 2.5f, center = Offset(centro.x + tamano / 4, centro.y - tamano / 10))
            }
            TipoFigura.RAYO -> {
                val ruta = Path().apply {
                    moveTo(centro.x + tamano / 4, centro.y - tamano / 2)
                    lineTo(centro.x - tamano / 2, centro.y + tamano / 10)
                    lineTo(centro.x, centro.y + tamano / 10)
                    lineTo(centro.x - tamano / 4, centro.y + tamano / 2)
                    lineTo(centro.x + tamano / 2, centro.y - tamano / 10)
                    lineTo(centro.x, centro.y - tamano / 10)
                    close()
                }
                drawPath(ruta, color)
            }
        }
    }
}

@Composable
fun TarjetaOpcion(
    opcion: DatosOpcion,
    estaSeleccionado: Boolean,
    alSeleccionar: () -> Unit
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
                    shape = RoundedCornerShape(8.dp)
                ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                DibujarFigura(
                    tipoFigura = opcion.tipoFigura,
                    modifier = Modifier.size(35.dp),
                    color = Color(0xFF4A148C)
                )
            }
        }
    }
}
