package com.example.d8roller

sealed class Routes(val route: String) {
    object dcSet : Routes("dcSet") //se configura con el nombre de funcion y no de clase (!!)
    object diceRoller : Routes("diceRoller/{dcValue}") {
        fun createRoute(dcValue: String) = "diceRoller/$dcValue" //pasamos el valor a la clase lógica
    }
}