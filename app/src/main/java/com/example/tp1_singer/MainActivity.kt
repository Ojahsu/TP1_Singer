package com.example.tp1_singer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

class DestinationMain
class DestinationInteret
class DestinationPasInteret
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val backStack = remember { mutableStateListOf<Any>(DestinationMain()) }
    val viewModel = viewModel<MainViewmodel>()
    val diceState by viewModel.diceState.collectAsStateWithLifecycle()
    val isRoll by viewModel.onrRoll.collectAsStateWithLifecycle()
    val diceSpeed by viewModel.diceSpeed.collectAsStateWithLifecycle()
    val diceTime by viewModel.diceTime.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    val current = backStack.lastOrNull()
                    val titleText = when (current) {
                        is DestinationMain -> "Accueil"
                        is DestinationInteret -> "Inscription"
                        is DestinationPasInteret -> "Désolé"
                        else -> "Evènements"
                    }
                    Text(titleText)
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Ouvrir le menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Ouvrir le menu"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = { key ->
                    when (key) {
                        is DestinationMain -> NavEntry(key) {
                            Accueil(onClickInteresse = {
                                backStack.add(DestinationInteret())
                            }, onClickPasInteresse = {
                                backStack.add(DestinationPasInteret())
                            })
                        }
                        is DestinationInteret -> NavEntry(key) {
                            Inscription({ backStack.removeLastOrNull() },
                                { viewModel.rollDice(diceTime, diceSpeed) },
                                diceState,
                                isRoll,
                                diceTime,
                                diceSpeed)
                        }
                        is DestinationPasInteret -> NavEntry(key) {
                            Desole({ backStack.removeLastOrNull() })
                        }
                        else -> {
                            error("Unknown key $key")
                        }
                    }
                }
            )
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Main()
}




