package com.example.gridlogicprototipo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gridlogicprototipo.ui.screen.HomeScreen
import com.example.gridlogicprototipo.ui.screen.exercises.Exercise1Screen
import com.example.gridlogicprototipo.ui.screen.exercises.Exercise2Screen
import com.example.gridlogicprototipo.ui.screen.exercises.Exercise3Screen

@Composable
fun AppNavigation(modifier : Modifier){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home){
        composable<Home>{HomeScreen(onGoToExercise1 = {navController.navigate(Exercise1)}, modifier = modifier)}
        composable<Exercise1>{Exercise1Screen(onNext = {navController.navigate(Exercise2)}, modifier = modifier)}
        composable<Exercise2>{Exercise2Screen(onNext = {navController.navigate(Exercise3)}, modifier = modifier)}
        composable<Exercise3>{Exercise3Screen(onNext = { /* Futuro */ }, modifier = modifier)}
    }
}