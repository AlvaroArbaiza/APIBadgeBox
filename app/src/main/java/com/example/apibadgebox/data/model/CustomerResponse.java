package com.example.apibadgebox.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerResponse {
    @SerializedName("customers")
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

}
