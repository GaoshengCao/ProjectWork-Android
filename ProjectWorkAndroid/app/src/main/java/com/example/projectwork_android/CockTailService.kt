import android.util.Log
import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



data class Drink(
    val strDrink: String,
    val strDrinkThumb: String?,
    val strInstructions: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?
)
data class DrinkResponce( val drinks: List<Drink> )


data class Ingridient(val strIngredient1: String)

data class IngridientResponce( val drinks: List<Ingridient>)



interface CocktailApiService {
    @GET("search.php")
    suspend fun searchDrinks(@Query("s") query: String): DrinkResponce

    @GET("list.php")
    suspend fun getIngridients(@Query("i") query: String): IngridientResponce
}

// Retrofit Instance
object RetrofitInstance {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    val api: CocktailApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Ensure this URL is correct
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApiService::class.java)
    }
}


suspend fun fetchIngridients(): List<String> {
    return try {
        // Fetch the ingredients list from the API
        val response = RetrofitInstance.api.getIngridients("list")

        // Map the list of ingredients to a list of strings
        response.drinks.map { it.strIngredient1 }
    } catch (e: Exception) {
        // Log the error and return a list with an error message
        Log.e("fetchIngridients", "Error fetching ingredients", e)
        listOf("Error fetching ingredients")
    }
}
