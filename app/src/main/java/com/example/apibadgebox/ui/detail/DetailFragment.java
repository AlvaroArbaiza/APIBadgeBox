package com.example.apibadgebox.ui.detail;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apibadgebox.R;
import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.data.service.SingleCustomerService;
import com.example.apibadgebox.ui.home.HomeFragment;
import com.example.apibadgebox.ui.kotlinTest.BaseKotlin;
import com.example.apibadgebox.ui.update.UpdateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailFragment extends Fragment {

    private DetailViewModel mViewModel;
    private String apiKey = "5277ff227ef6b66797d4c934025a2f8a";
    private String apiSecret = "02a7cef518d041d9732e05fc0b5e4430";
    private String baseUrl = "https://www.badgebox.com/api/v1/";
    private Customer customer;
    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        String customerID = bundle.getString("selectedCustomer");
        Log.e("bundle", "bundle: " + customerID);

        TextView nameDetail = v.findViewById(R.id.textViewName);
        TextView addressDetail = v.findViewById(R.id.textViewAddress);
        TextView numberDetail = v.findViewById(R.id.textViewNumber);
        TextView zipCodeDetail = v.findViewById(R.id.textViewZipCode);
        TextView cityDetail = v.findViewById(R.id.textViewCity);
        TextView countryDetail = v.findViewById(R.id.textViewCountry);
        TextView pecDetail = v.findViewById(R.id.textViewEmailCert);
        TextView sdiDetail = v.findViewById(R.id.textViewSDI);
        TextView fiscalCodeDetail = v.findViewById(R.id.textViewFiscalCode);
        TextView emailDetail = v.findViewById(R.id.textViewEmail);
        TextView phoneDetail = v.findViewById(R.id.textViewPhone);
        TextView faxDetail = v.findViewById(R.id.textViewFax);
        TextView NoteDetail = v.findViewById(R.id.textViewNote);

        Retrofit retrofit =
                new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        SingleCustomerService retrofitService = retrofit.create(SingleCustomerService.class);
        // GET CUSTOMER
        if(!customerID.equalsIgnoreCase(null))
        {
            Call<Customer> call = retrofitService.getCustomer(apiKey, apiSecret, Integer.parseInt(customerID));
            call.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(@NonNull Call<Customer> call,
                                       @NonNull Response<Customer> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        customer = response.body();
                        Log.e("Poraaa", "resp: " + response.body().toString());

                        nameDetail.setText(customer.getName());
                        addressDetail.setText(customer.getAddress());
                        numberDetail.setText(Integer.toString(customer.getNumber()));
                        zipCodeDetail.setText(Integer.toString(customer.getZipcode()));
                        cityDetail.setText(customer.getCity());
                        countryDetail.setText(customer.getCountry());
                        pecDetail.setText(customer.getEmail_cert());
                        sdiDetail.setText(customer.getSdi());
                        fiscalCodeDetail.setText(customer.getFiscal_code());
                        emailDetail.setText(customer.getEmail());
                        phoneDetail.setText(customer.getPhone());
                        faxDetail.setText(customer.getFax());
                        NoteDetail.setText(customer.getNote());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                    Log.e("fallito", "failure: " + t);
                    customer = null;
                }
            });

            Button buttonDelete = v.findViewById(R.id.buttonDelete);
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(getContext())
                        .setTitle("Conferma uscita")
                        .setMessage("Sicuro di voler eliminare l'utente?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // CALL DELETE METHOD
                                delete(customerID);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                FragmentManager fragmentManager = getParentFragmentManager();
                                DetailFragment detailFragment = new DetailFragment();
                                HomeFragment homeFragment = new HomeFragment();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.detail_fragment, homeFragment)
                                        .commit();

                                // chiusura dialog
                                dialog.dismiss();
                            }
                        })
                        .show();
                }
            });

            Button buttonUpdate = v.findViewById(R.id.buttonUpdate);
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update(customerID);
                }
            });

        } else{
            Toast.makeText(getContext(),"Ciao", Toast.LENGTH_LONG).show();
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        BottomNavigationView bottomNavigationView = view.getRootView().findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int x = item.getItemId();
                /*if(x==R.id.navigation_delete)
                    Toast.makeText(getContext(),  "Esegue la delete e torna a home", Toast.LENGTH_LONG).show();
                else if(x==R.id.navigation_home)
                {
                    Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_nav_home);
                }*/

                if(x==R.id.navigation_notification)
                {
                    Intent i = new Intent(getContext(), BaseKotlin.class);
                    startActivity(i);
                }
                return true;
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        // TODO: Use the ViewModel
    }

    public void delete(String customerID){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();
        SingleCustomerService retrofitService = retrofit.create(SingleCustomerService.class);
        // DELETE
        Call<Void> callDelete = retrofitService.deleteCustomer(apiKey, apiSecret, Integer.parseInt(customerID));
        callDelete.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call,
                                   @NonNull Response response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("rispostaDelete", "risp: "+ response.isSuccessful());
                    Toast.makeText(getContext(), "Eliminazione avvenuta con successo!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.e("failureDelete", "failure: " + t);
            }
        });

        FragmentManager fragmentManager = getParentFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.detail_fragment, homeFragment)
                .commit();
    }

    public void update(String customerID){

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedCustomerUpdate", customerID );

        FragmentManager fragmentManager = getParentFragmentManager();
        UpdateFragment updateFragment = new UpdateFragment();
        updateFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.detail_fragment, updateFragment)
                .commit();
    }
}