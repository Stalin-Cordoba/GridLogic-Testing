package com.example.gridlogicprototipo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gridlogicprototipo.ui.theme.Purple40
import com.example.gridlogicprototipo.ui.theme.DarkPurple
import com.example.gridlogicprototipo.ui.theme.DarkBlue

@Composable
fun HomeScreen(onGoToExercise1: () -> Unit, modifier : Modifier){

    Column(modifier = Modifier.fillMaxSize().background(brush = verticalGradient(colors = listOf(DarkPurple,
        Purple40
    ))),
        horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "GridLogic",
            fontSize = 36.sp,
            color = Purple40,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(35.dp))

        Text(text = "Este es un test que evalúa tu capacidad de razonamiento. " +
                "Está basado en las matrices progresivas de Raven",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "INSTRUCCIONES",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(8.dp))

        Card(shape = RoundedCornerShape(10.dp),
            modifier = Modifier.height(230.dp).padding(horizontal = 22.dp)){
            Column(modifier = Modifier.fillMaxSize().background(DarkBlue),
                horizontalAlignment = Alignment.CenterHorizontally){

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "1. En cada ejercicio, se le mostrará una matriz, la cual lleva consigo un patrón",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "2. En cada matriz, hay una figura faltante al final",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "3. Usted debe elegir entre las opciones, la figura que" +
                        " tiene la mayor posibilidad de ser la faltante",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "No hay límite de tiempo, por lo que puede ir a su ritmo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = { onGoToExercise1() }){
            Text(text = "Empezar", textAlign = TextAlign.Center)
        }
    }
}