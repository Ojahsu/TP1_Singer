package com.example.tp1_singer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage



@Composable
fun Accueil(onClickInteresse: () -> Unit, onClickPasInteresse: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = "https://isis.univ-jfc.fr/sites/isis.univ-jfc.fr/files/images-contenu/2024-09/Affiche_Forum.png",
            contentDescription = "Bannière de l'évènement",
        )
        Column {
            Row {
                Text(fontWeight = FontWeight.Bold, text = "Où :")
                Text(text = " Ecole ingénieur ISIS")
            }
            Row {
                Text(fontWeight = FontWeight.Bold, text = "Quand :")
                Text(text = " 24 octobre")
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Button(onClick = { onClickInteresse() }) { Text("Inscription") }
            OutlinedButton(
                onClick = { onClickPasInteresse() },
                shape = RoundedCornerShape(50),
                border = BorderStroke(2.dp, Color(0xFFD8DBE8)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF455B8A),
                    disabledContentColor = Color(0xFF9AA3B2),
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text("Pas intéressé")
            }
        }
    }
}