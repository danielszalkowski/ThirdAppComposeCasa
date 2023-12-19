package com.pmdm.thirdappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thirdappcompose.R
import com.example.thirdappcompose.ui.theme.ThirdAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirdAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun Principal() {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            val (checkbox, texto1, texto2, texto3, botonIzquierda, botonDerecha, carrusel) = createRefs()
            val checkedState = remember { mutableStateOf(false) }
            val barrera = createBottomBarrier(checkbox, texto2)
            var i by remember { mutableStateOf(0) }
            val imagenes = listOf(
                R.drawable.forwardx_yes_cliente,
                R.drawable.hosts_virtuales,
                R.drawable.instalacion_postfix,
                R.drawable.instalacion_ssh,
                R.drawable.instalacion_vsftpd
            )

            val modifierImagen = Modifier
                .size(60.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Cyan)

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                modifier = Modifier.constrainAs(checkbox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            )
            Text(text = "Hola",
                modifier = Modifier.constrainAs(texto1) {
                    start.linkTo(checkbox.end)
                    top.linkTo(checkbox.top)
                    bottom.linkTo(checkbox.bottom)
                })
            if (!checkedState.value) {
                Text(text = "Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad Feliz Navidad",
                    modifier = Modifier.constrainAs(texto2){
                        top.linkTo(checkbox.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                    textAlign = TextAlign.Center
                )
            }


            Text(text =
            if(checkedState.value) {"El botón está pulsado"} else {"El botón no está pulsado"},
                modifier = Modifier.constrainAs(texto3){
                    if (checkedState.value) {
                        top.linkTo(barrera)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    } else {
                        top.linkTo(barrera)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                })
            Button(onClick = { i = (i - 1 + imagenes.size) % imagenes.size },
                modifier = Modifier.constrainAs(botonIzquierda){
                    bottom.linkTo(carrusel.bottom)
                    top.linkTo(carrusel.top)
                    end.linkTo(carrusel.start)
                }) {
                Text(text = "←")
            }

            Button(onClick = { i = (i + 1) % imagenes.size },
                modifier = Modifier.constrainAs(botonDerecha){
                    bottom.linkTo(carrusel.bottom)
                    top.linkTo(carrusel.top)
                    start.linkTo(carrusel.end)
                }) {
                Text(text = "→")
            }

            Image(
                painter = painterResource(id = imagenes[i]),
                contentDescription = null,
                modifier = modifierImagen.constrainAs(carrusel) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}