package com.example.tp1_singer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Inscription(onClickRetour: () -> Unit,
                onRollDice: () -> Unit,
                diceValue: Int,
                isRoll: Boolean,
                diceTime: Int,
                diceSpeed: Long) {

    val viewModel = viewModel<MainViewmodel>()
    val diceResource = when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = diceResource),
            contentDescription = "Logo de l'école",
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Button(onClick = { onClickRetour() }) { Text("Retour") }
            OutlinedButton(
                onClick = { onRollDice() },
                shape = RoundedCornerShape(50),
                border = BorderStroke(2.dp, Color(0xFFD8DBE8)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF455B8A),
                    disabledContentColor = Color(0xFF9AA3B2),
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text("Roll Dice")
            }
        }
        Column {
            Text("Vitesse: ${(500f-diceSpeed)}")

            Slider(
                value = (500f-diceSpeed).toFloat(),
                onValueChange = { longValue ->
                    viewModel.setRollSpeed((500f-longValue).toLong())
                },
                valueRange = 10f..500f,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }

        Column {
            Text("Temps: ${(diceTime*diceSpeed)/1000}s")

            Slider(
                value = diceTime.toFloat(),
                onValueChange = { longValue ->
                    viewModel.setRollTime(longValue.toInt())
                },
                valueRange = 10f..100f,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}