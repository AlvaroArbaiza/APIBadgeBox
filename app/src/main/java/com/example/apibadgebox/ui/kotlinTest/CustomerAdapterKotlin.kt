package com.example.apibadgebox.ui.kotlinTest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apibadgebox.R
import com.example.apibadgebox.data.model.Customer

class CustomersAdapterKotlin(private var customerList: List<Customer>, private val context: Context) :
        RecyclerView.Adapter<CustomersAdapterKotlin.CustomerViewHolder>() {
    fun setData(newData: List<Customer>) {
        customerList = newData.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return CustomerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customerList[position]
        holder.nomeCustomer.text = customer.name
        holder.emailCustomer.text = customer.email
        holder.addressCustomer.text = customer.address

        // Utilizza Glide per il caricamento delle immagini (aggiungere dipendenza nel file gradle)
        // Glide.with(context).load(customer.thumbnailUrl).into(holder.thumbnailImageView)
    }

    override fun getItemCount(): Int = customerList.size

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeCustomer: TextView = itemView.findViewById(R.id.nomeCustomer)
        val emailCustomer: TextView = itemView.findViewById(R.id.emailCustomer)
        val addressCustomer: TextView = itemView.findViewById(R.id.addressCustomer)
        // val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
    }

}