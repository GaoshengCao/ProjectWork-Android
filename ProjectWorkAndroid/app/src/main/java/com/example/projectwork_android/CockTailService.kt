import android.telecom.Call
import com.example.projectwork_android.Drink
import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface TodoApiService {
    @GET("search.php?s={name}")
    suspend fun getDrink(@Path("name") name: String)

// Retrofit instance
object RetrofitInstance {
    // Sostituiscilo con il tuo API KEY
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    val api: TodoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApiService::class.java)
    }
}
}

