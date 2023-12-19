package com.example.apibadgebox.data.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apibadgebox.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.ui.detail.DetailFragment;

import java.util.List;

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder> {

    private List<Customer> customerList;
    private Context context;
    private FragmentManager fragmentManager;
    public CustomersAdapter(List<Customer> customerList, Context context, FragmentManager fragmentManager) {
        this.customerList = customerList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.nomeCustomer.setText(customer.getName());
        holder.emailCustomer.setText(customer.getEmail());
        holder.addressCustomer.setText(customer.getAddress());



        // Utilizza Glide per il caricamento delle immagini (aggiungere dipendenza nel  file gradle)
        //Glide.with(context).load(customer.getThumbnailUrl()).into(holder.thumbnailImageView);

        // listener sugli elementi del RecycleView
        // la funzione anonima è più concisa ma equivalente a listener consueto
        /*holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(customerList.get(position));
                Toast.makeText(context, "Elemento cliccato: " + customer.getName(), Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Elemento cliccato: " + customer.getId(), Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedCustomer", Integer.toString(customer.getId()));
                //bundle.putSerializable("selectedCustomer", customer);

                // creo l'istanza di DetailFragment
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);

                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_detailFragment, bundle);
                /*fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, detailFragment)
                        .commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView nomeCustomer;
        TextView emailCustomer;
        TextView addressCustomer;
        ImageView thumbnailImageView;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCustomer = itemView.findViewById(R.id.nomeCustomer);
            emailCustomer = itemView.findViewById(R.id.emailCustomer);
            addressCustomer = itemView.findViewById(R.id.addressCustomer);
            //thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }
}
