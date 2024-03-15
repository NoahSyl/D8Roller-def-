package com.example.d8roller

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.security.AccessController


@Composable

fun diceRoller(navController: NavController, dcValue: String) { //lógica del programa

    var dice1 by remember {
        mutableStateOf(1)
    }

    var dice2 by remember {
        mutableStateOf(0)
    }

    var advantage by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Image(  //primer dado
                painter = selectImage(dice1),
                contentDescription = "Primer dado"
            )

            if(advantage) { //activa la segunda imagen

                Image( //segundo dado
                    painter = selectImage(dice2),
                    contentDescription = "Primer dado"
                )
            }

        }

        Button(
            onClick = { //al presionar nos genera un número random para el dado
                dice1 = randomNumber()

                advantage = false //cierra la segunda imagen si estaba presente

                if(dcValue.toInt() <= dice1){ //si el valor ramndom > valor del DC muestra toast

                    Toast.makeText(context, "Has superado la tirada", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
        )
        {
            Text(text = "Roll the dice")
        }

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){

            Button(onClick = {

                dice1 = randomNumber() //genera valores random para los dos dados
                dice2 = randomNumber()

                advantage = true //muestra segundo dado

                if(dice1 >= dice2 && dice1 >= dcValue.toInt() || dice2>=dice1 && dice2 >= dcValue.toInt()){ //escoge el dado con mayor resultado y lo compara con el DC
                    Toast.makeText(context, "Has superado la tirada", Toast.LENGTH_SHORT).show()
                }

            }, modifier = Modifier.weight(1f).padding(15.dp)) {

                Text(text = "Advantage")
            }

            Button(onClick = {

                dice1 = randomNumber()
                dice2 = randomNumber()

                advantage = true

                if(dice1 <= dice2 && dice1 >= dcValue.toInt() || dice2<=dice1 && dice2 >= dcValue.toInt()){ //escoge el dado con menor resultado y lo compara con el DC
                    Toast.makeText(context, "Has superado la tirada", Toast.LENGTH_SHORT).show()
                }

            }, modifier = Modifier.weight(1f).padding(15.dp)) {

                Text(text = "Disadvantage")
            }

        }





        Button(
            onClick = {

                navController.navigate(Routes.dcSet.route) //te lleva de vuelta a la pantalla de DC y reinicia los datos

            },
            modifier = Modifier.padding(30.dp)
        )
        {
            Text(text = "Reinicia")
        }

    }


}

@Composable
fun selectImage(dice1: Int): Painter { //elige la imagen del dado

    val imageResource = //creamos una variable para guardar las imagenes
        when (dice1) {
            1 -> R.drawable.d1
            2 -> R.drawable.d2
            3 -> R.drawable.d3
            4 -> R.drawable.d4
            5 -> R.drawable.d5
            6 -> R.drawable.d6
            7 -> R.drawable.d7
            else -> R.drawable.d8
        }

    return painterResource(id = imageResource)

}

fun randomNumber(): Int { //crea el número random
    return (1..8).random()
}



