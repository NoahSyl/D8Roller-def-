package com.example.d8roller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.d8roller.ui.theme.D8RollerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            D8RollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navigationController = rememberNavController() //inicializamos y ejecutamos el navigator
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.dcSet.route
                    ) {
                        composable(Routes.dcSet.route) { dcSet(navigationController) }
                        composable( //a esta función hay que pasarle un parámetro
                            Routes.diceRoller.route,
                            arguments = listOf(navArgument("dcValue") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            diceRoller(
                                navigationController,
                                backStackEntry.arguments?.getString("dcValue").orEmpty() //orEmpty para asegurarse de que el String contiene datos
                            )
                        }
                    }
                }
            }
        }
    }
}
