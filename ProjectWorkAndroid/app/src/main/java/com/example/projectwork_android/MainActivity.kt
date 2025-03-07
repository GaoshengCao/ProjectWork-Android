package com.example.projectwork_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectwork_android.ui.theme.ProjectWorkAndroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectWorkAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//Schermata iniziale
//Lista CheckList Bevante Datte
//Checklist Inventario
//Checklist Ricette Fattibili // Segnare Che ricesso sono state fatte e quali sono ancora da fare
//possinilità di entrare e vedere le ricette
//Funzione di ricerca per bevande fatta bene
//  Per Ingrediente
//  Per Nome

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()
    val apiService = TodoApiService.RetrofitInstance.api
    var textciao by remember { mutableStateOf("") }


    coroutineScope.launch {
        textciao = apiService.getDrink("Beer").toString()
    }

    Text(
        text = textciao,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectWorkAndroidTheme {
        Greeting("I WANT TO DIE")
    }
}