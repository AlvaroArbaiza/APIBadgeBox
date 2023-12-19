package com.example.apibadgebox.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apibadgebox.data.adapter.CustomersAdapter;
import com.example.apibadgebox.data.interfacce.CustomersCallback;
import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.data.model.CustomerResponse;
import com.example.apibadgebox.data.service.CustomerService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitIstance{

    private String apiKey = "5277ff227ef6b66797d4c934025a2f8a";
    private String apiSecret = "02a7cef518d041d9732e05fc0b5e4430";
    private int page = 0;
    private int limit = 10;
    private List<Customer> customers;
    private Customer customer;

    public void readCustomers(CustomersAdapter customerAdapter, CustomersCallback callback) {

        String baseUrl = "https://www.badgebox.com/api/v1/";
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();

        CustomerService retrofitService = retrofit.create(CustomerService.class);

        // GET ALL CUSTOMERS
        Call<CustomerResponse> call = retrofitService.getCustomers(apiKey, apiSecret, page, limit);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(@NonNull Call<CustomerResponse> call,
                                   @NonNull Response<CustomerResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    customers = response.body().getCustomers();
                    customerAdapter.notifyDataSetChanged();
                    callback.onCustomerDataReceived(customers);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomerResponse> call, @NonNull Throwable t) {
                Log.e("failure", "failure: " + t);
                customers = null;
                callback.onCustomerDataFailed(t);
            }
        });
    }
}
