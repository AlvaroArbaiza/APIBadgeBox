package com.example.apibadgebox.data.service;

import com.example.apibadgebox.data.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface SingleCustomerService {

    @GET("customer/")
    Call<Customer> getCustomer(
            @Query("api_key") String apikey,
            @Query("api_secret") String apiSecret,
            @Query("id") int id
    );

    @DELETE("customer/")
    Call<Void> deleteCustomer(
            @Query("api_key") String apikey,
            @Query("api_secret") String apiSecret,
            @Query("id") int id
    );

    @PUT("customer/")
    Call<Void> updateCustomer(
            @Query("api_key") String apikey,
            @Query("api_secret") String apiSecret,
            @Query("id") int id,
            @Body Customer requestable

    );
}
