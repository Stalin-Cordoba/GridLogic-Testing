package com.example.gridlogicprototipo.ui.screen.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gridlogicprototipo.ui.theme.DarkPurple
import com.example.gridlogicprototipo.ui.theme.Purple40

@Composable
fun Exercise1Screen(modifier : Modifier){

    Column(modifier = Modifier.fillMaxSize().background(brush = verticalGradient(colors = listOf(DarkPurple,
        Purple40
    ))),
        horizontalAlignment = Alignment.CenterHorizontally){

        /*Esta es la plantilla general para la pantalla de cada ejercicio de la prueba*/

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Hola mundo",
            fontSize = 36.sp,
            color = Purple40,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)
    }
}