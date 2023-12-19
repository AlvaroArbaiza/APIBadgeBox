package com.example.apibadgebox.data.service;

import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.data.model.CustomerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CustomerService {
    @GET("company/customers")
    Call<CustomerResponse> getCustomers(
            @Query("api_key") String apiKey,
            @Query("api_secret") String apiSecret,
            @Query("page") int page,
            @Query("limit") int limit

    );

    @POST("customer/")
    Call<Customer> postCustomer(
            @Query("api_key") String apiKey,
            @Query("api_secret") String apiSecret,
            @Body Customer requestable
    );
}
