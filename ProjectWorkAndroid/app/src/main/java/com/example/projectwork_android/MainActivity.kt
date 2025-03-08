package com.example.projectwork_android

import Drink
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectwork_android.ui.theme.ProjectWorkAndroidTheme
//import fetchAllDrinks
import fetchIngridients
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectWorkAndroidTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { MainPage(navController) }
                        composable("screen2") { CompletionPage(navController) }
                        composable("screen3") { InverntoryPage(navController) }
                        composable("screen4") { SearchPage(navController) }
                    }
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainPage(navController: NavHostController) {
    //Schermata di navigazione ad altre schermate

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Recipe Tracker", fontSize = 20.sp)
        Button(onClick = {navController.navigate("screen2")}) {
            Text(text = "Lista Completamento")
        }
        Button(onClick = {navController.navigate("screen3")}) {
            Text(text = "Inventario Ingredienti")
        }
        Button(onClick = {navController.navigate("screen4")}) {
            Text(text = "Ricerca Ricette")
        }

    }

}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CompletionPage(navController: NavHostController,modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var DrinkList by remember { mutableStateOf<List<Drink>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    coroutineScope.launch {
//        DrinkList = fetchAllDrinks()
        isLoading = false
    }

    //Header
    Column {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Button(onClick = { navController.navigate("screen1") }) {
                    Text(text = "<")
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Completion",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {  }
        }


        var scrollState = rememberScrollState()
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            )
        } else {
            LazyColumn(modifier = modifier) {
                items(DrinkList) { Drink ->
                    Text(text = Drink.strDrink)
                }
            }
        }
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun InverntoryPage(navController: NavHostController,modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var ingredientList by remember { mutableStateOf<List<String>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    coroutineScope.launch {
        ingredientList = fetchIngridients()
        isLoading = false
    }

    //Header
    Column {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Button(onClick = { navController.navigate("screen1") }) {
                    Text(text = "<")
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Inventory",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {  }
        }


        var scrollState = rememberScrollState()
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            )
        } else {
            LazyColumn(modifier = modifier) {
                items(ingredientList) { ingredient ->
                    Text(text = ingredient)
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchPage(navController: NavHostController) {
    //Schermata che ti mostra quali bevande puoi fare con gli ingredienti che hai

    //TODO
    //Intero Funzionamento

    //Header
    Column {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Button(onClick = { navController.navigate("screen1") }) {
                    Text(text = "<")
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Inventory",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) { }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RecipePage(navigator: NavHostController){
    //Contenuto Pagina
    //Immagine Drink
    //Ingredienti
    //Ricetta
    //Tasto per indicare che è stato completato
}

