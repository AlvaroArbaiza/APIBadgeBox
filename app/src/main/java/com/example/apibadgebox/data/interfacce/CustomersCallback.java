package com.example.apibadgebox.data.interfacce;

import com.example.apibadgebox.data.model.Customer;

import java.util.List;

public interface CustomersCallback {

    void onCustomerDataReceived(List<Customer> customers);
    void onCustomerDataFailed(Throwable t);
}
