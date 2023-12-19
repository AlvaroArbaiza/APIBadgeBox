package com.example.apibadgebox.ui.kotlinTest

import com.example.apibadgebox.data.model.Customer
import com.google.gson.annotations.SerializedName

data class CustomerResponseKotlin(
        @SerializedName("customers")
        val customers: List<Customer>
)
