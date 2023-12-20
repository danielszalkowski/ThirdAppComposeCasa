package com.example.thirdappcompose.model

sealed class Routes (val route: String) {
    object Screen1: Routes("Screen1")
    object Screen2: Routes("Screen2")
    object MainScreen: Routes("Principal")
}