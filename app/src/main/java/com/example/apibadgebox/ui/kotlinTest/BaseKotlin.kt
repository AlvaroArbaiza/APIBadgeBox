package com.example.apibadgebox.ui.kotlinTest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apibadgebox.R
import com.example.apibadgebox.data.AuthorizationInterceptor
import com.example.apibadgebox.data.model.Customer
import com.example.apibadgebox.databinding.ActivityKotlinBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseKotlin : AppCompatActivity() {

    private lateinit var customers: List<Customer>
    private lateinit var binding: ActivityKotlinBinding
    private var customerAdapter: CustomersAdapterKotlin? = null
    var data: List<Customer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        /*
        // gli elementi di LISTOF sono immutabili
        val lista = listOf("one","two","three")

        // la dimensione è fissa e gli elementi sono mutabili
        val array = arrayOf("one","two","three")

        for (element in array)
        {
            Log.i("array","dato: " + element)
        }

        for(i in 1..10)
        {

        }

        val n: Int = 10   // Long, Double, Float, Boolean(true,false), String \\
        val x = 11
        Log.i("numero", "numero: " + x.toString())

        val listaString : MutableList<String> = mutableListOf("Archimede", "Raffaello", "Michelangelo")
        listaString.add("Leonardo")
        listaString.size
        Log.i("lista", "size: " + lista.size)
        Log.i("lista", "first: " + lista.first())
        Log.i("lista", "last: " + lista.last())
        */
        /*------------------------------------------------------------------------------------------------------*/
        binding = ActivityKotlinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Configura l'interceptor per il logging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // o Level.BASIC per avere meno dettagli
        }

        // kotlin può usare una classe java
        val authorizationInterceptor = AuthorizationInterceptor()

        // Configura il client OkHttpClient con gli interceptor (apiKey e ApiSecret)
        /*val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authorizationInterceptor)
                .build()*/

        // Configura Retrofit con il client
        val retrofit: Retrofit =
                Retrofit.Builder()
                .baseUrl("https://www.badgebox.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client)
                .build()

        // Crea un'istanza di JsonPlaceholderApi
        val api = retrofit.create(TestCoRoutine.JsonPlaceholderApi::class.java)

        // Crea un'istanza di TestCoRoutine passando l'istanza di JsonPlaceholderApi
        //val testCoRoutine = TestCoRoutine(api)

        val recycler = binding.recyclerViewKotlin
        recycler.layoutManager = LinearLayoutManager(this)
        data = ArrayList()


        // Chiama il metodo fetchData in modo asincrono
        runBlocking {
            launch(Dispatchers.IO) {
                //testCoRoutine.fetchData()
                customers = api.getData(
                        authorizationInterceptor.apiKey,
                        authorizationInterceptor.apiSecret,
                        authorizationInterceptor.page,
                        authorizationInterceptor.limit
                ).customers
                customerAdapter = CustomersAdapterKotlin(customers, applicationContext)
                recycler.adapter = customerAdapter
                /*withContext(Dispatchers.Main) {
                    customerAdapter?.setData(customers)
                    customerAdapter?.notifyDataSetChanged()
                }*/
            }
        }

        Log.i("coroutine", "costumers : $customers")
    }
}