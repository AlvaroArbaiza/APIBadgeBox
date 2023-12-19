package com.example.apibadgebox.ui.kotlinTest

// richiede libreria coroutine nel file gradle implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

import android.util.Log
import com.example.apibadgebox.data.model.Customer
import retrofit2.http.GET
import retrofit2.http.Query

class TestCoRoutine(private val api: JsonPlaceholderApi) {

    private val apiKey = "5277ff227ef6b66797d4c934025a2f8a"
    private val apiSecret = "02a7cef518d041d9732e05fc0b5e4430"
    private val limit = 10
    private val page = 0

    interface JsonPlaceholderApi {
        @GET("company/customers")
        suspend fun getData(
                @Query("api_key") apiKey: String,
                @Query("api_secret") apiSecret: String,
                @Query("page") page: Int,
                @Query("limit") limit: Int
        ): CustomerResponseKotlin
    }

    // Esegue la chiamata di rete in modo asincrono
    suspend fun fetchData() {
        try {
            Log.i("coroutine", "prima di getData")

            val customers: List<Customer> = api.getData(apiKey, apiSecret, limit, page).customers

            for (customer in customers) {
                // Fai qualcosa con ogni singolo oggetto Customer
                Log.i("coroutine", "address: ${customer.address}")
            }

        } catch (e: Exception) {
            Log.e("coroutine", Log.getStackTraceString(e))
            e.printStackTrace()
        }
    }
}