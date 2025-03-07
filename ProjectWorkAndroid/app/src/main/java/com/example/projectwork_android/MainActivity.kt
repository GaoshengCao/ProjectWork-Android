package com.example.projectwork_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectwork_android.ui.theme.ProjectWorkAndroidTheme
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
fun CompletionPage(navController: NavHostController){

    Button(onClick = {navController.navigate("screen1")}) {
        Text(text = "Back")
    }
    //Schermata in cui vengono mostrati quali bevande sono state fatte e quali no

    //TODO
    //Filtro Per vedere Queli non selezionati

}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun InverntoryPage(navController: NavHostController,modifier: Modifier = Modifier){
    val coroutineScope = rememberCoroutineScope()
    var ingredientList by remember { mutableStateOf<List<String>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch ingredients asynchronously
    coroutineScope.launch {
        ingredientList = fetchIngridients()  // Update the list when fetched
        isLoading = false  // Set loading to false once data is fetched
    }

    if (isLoading) {
        // Show a loading indicator while data is being fetched
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        // Display ingredients in a list
        LazyColumn(modifier = modifier) {
            items(ingredientList) { ingredient ->
                Text(text = ingredient)
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchPage(navController: NavHostController){
    //Schermata che ti mostra quali bevande puoi fare con gli ingredienti che hai

    //TODO
    //Intero Funzionamento

    Button(onClick = {navController.navigate("screen1")}) {
        Text(text = "Back")
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RecipePage(navigator: NavHostController){

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainPage(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var ingredientList by remember { mutableStateOf<List<String>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch ingredients asynchronously
    coroutineScope.launch {
        ingredientList = fetchIngridients()  // Update the list when fetched
        isLoading = false  // Set loading to false once data is fetched
    }

    if (isLoading) {
        // Show a loading indicator while data is being fetched
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        // Display ingredients in a list
        LazyColumn(modifier = modifier) {
            items(ingredientList) { ingredient ->
                Text(text = ingredient)
            }
        }
    }
}


//{
//    //Schermata che ti permette di segnare quali ingredienti hai
//
//
//
//    //TODO
//    //CheckBox Per ingrediente
//    //Tasto per selezionare tutti gli ingredienti
//    //Tasto per deselezionare tutti gli ingredienti
//
//    val coroutineScope = rememberCoroutineScope()
//    var ingredientList by remember { mutableStateOf<List<String>>(emptyList()) }
//    var isLoading by remember { mutableStateOf(true) }
//
//    // Fetch ingredients asynchronously
//    coroutineScope.launch {
//        ingredientList = fetchIngridients()  // Update the list when fetched
//        isLoading = false  // Set loading to false once data is fetched
//    }
//
////    if (isLoading) {
////        // Show a loading indicator while data is being fetched
////        CircularProgressIndicator(modifier)
////    }
////    else {
//    // Display ingredients in a list
//    var scrollState = rememberScrollState()
//    LazyColumn(Modifier.verticalScroll(scrollState)) {
//        items(ingredientList) { ingredient ->
//            Text(text = ingredient)
//        }
//    }
////    }
//
//
//}
