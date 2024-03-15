package com.example.d8roller

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable

fun dcSet(navController: NavController) {

    var dcValue by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = dcValue,
            onValueChange = { dcValue = it },
            label = { Text(text = "Introduce tu DC") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) //teclado numerico
        )

        Button(
            onClick = {

                if (dcValue.toInt() > 8 || dcValue.toInt() < 0) { //control de errores para no introducir datos incorrectos

                    Toast.makeText(context, "Introduce un valor entre 1 y 8", Toast.LENGTH_SHORT).show() //aviso del error
                } else {

                    navController.navigate(Routes.diceRoller.createRoute(dcValue)) //envía el dato a la clase de lógica
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {

            Text(text = "Tira los dados")

        }

    }
}