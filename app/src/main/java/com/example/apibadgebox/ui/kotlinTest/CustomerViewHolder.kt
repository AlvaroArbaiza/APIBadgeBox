package com.example.apibadgebox2.ui.customer.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.apibadgebox.data.model.Customer
import com.example.apibadgebox.databinding.ItemCustomerBinding

class CustomerViewHolder(private val binding: ItemCustomerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindCustomerCardView(cardView: Customer) {
        binding.nomeCustomer.text = cardView.address
        /*binding.idField.text = cardView.id.toString()
        binding.nameField.text = "name: ${cardView.name}"
        binding.addressField.text = "address: ${cardView.address}"
        binding.numberField.text = "number: ${cardView.number}"
        binding.cityField.text = "city: ${cardView.city}"
        binding.zipcodeField.text = "zipcode: ${cardView.zipcode}"
        binding.cityField.text = "country: ${cardView.country}"
        binding.sdiField.text = "sdi: ${cardView.sdi}"
        binding.fiscalCodeField.text = "fiscalCode: ${cardView.fiscalCode}"
        binding.emailField.text = "email: ${cardView.email}"
        binding.phoneField.text = "phone: ${cardView.phone}*/
    }
}