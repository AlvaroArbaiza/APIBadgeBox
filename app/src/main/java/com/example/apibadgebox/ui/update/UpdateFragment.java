package com.example.apibadgebox.ui.update;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apibadgebox.R;
import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.data.service.CustomerService;
import com.example.apibadgebox.data.service.SingleCustomerService;
import com.example.apibadgebox.ui.update.UpdateFragment;
import com.example.apibadgebox.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateFragment extends Fragment {

    private UpdateViewModel mViewModel;
    private String apiKey = "5277ff227ef6b66797d4c934025a2f8a";
    private String apiSecret = "02a7cef518d041d9732e05fc0b5e4430";
    private String baseUrl = "https://www.badgebox.com/api/v1/";
    private Customer customer;

    public static UpdateFragment newInstance() {
        return new UpdateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update, container, false);

        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();

        EditText numberCustomer = v.findViewById(R.id.numberUpdate);
        EditText zipcodeCustomer = v.findViewById(R.id.zipcodeUpdate);
        EditText nameCustomer = v.findViewById(R.id.nameUpdate);
        EditText addressCustomer = v.findViewById(R.id.addressUpdate);
        EditText cityCustomer = v.findViewById(R.id.cityUpdate);
        EditText countryCustomer = v.findViewById(R.id.countryUpdate);
        EditText emailCertCustomer = v.findViewById(R.id.pecUpdate);
        EditText sdiCustomer = v.findViewById(R.id.sdiUpdate);
        EditText fiscalCodeCustomer = v.findViewById(R.id.cfUpdate);
        EditText emailCustomer = v.findViewById(R.id.emailUpdate);
        EditText phoneCustomer = v.findViewById(R.id.phoneUpdate);
        EditText faxCustomer = v.findViewById(R.id.faxUpdate);
        EditText noteCustomer = v.findViewById(R.id.noteUpdate);

        SingleCustomerService retrofitService = retrofit.create(SingleCustomerService.class);
        Bundle bundle = getArguments();
        String customerID = bundle.getString("selectedCustomerUpdate");

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

                        nameCustomer.setText(customer.getName());
                        addressCustomer.setText(customer.getAddress());
                        numberCustomer.setText(Integer.toString(customer.getNumber()));
                        zipcodeCustomer.setText(Integer.toString(customer.getZipcode()));
                        cityCustomer.setText(customer.getCity());
                        countryCustomer.setText(customer.getCountry());
                        emailCertCustomer.setText(customer.getEmail_cert());
                        sdiCustomer.setText(customer.getSdi());
                        fiscalCodeCustomer.setText(customer.getFiscal_code());
                        emailCustomer.setText(customer.getEmail());
                        phoneCustomer.setText(customer.getPhone());
                        faxCustomer.setText(customer.getFax());
                        noteCustomer.setText(customer.getNote());

                    }
                }

                @Override
                public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                    Log.e("fallito", "failure: " + t);
                    customer = null;
                }
            });

        } else{
            Toast.makeText(getContext(),"Noo", Toast.LENGTH_LONG).show();
        }

        Button button = v.findViewById(R.id.buttonUpdate2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = numberCustomer.getText().toString();
                String zipcode = zipcodeCustomer.getText().toString();
                String name = nameCustomer.getText().toString();
                String address = addressCustomer.getText().toString();
                String city = cityCustomer.getText().toString();
                String country = countryCustomer.getText().toString();
                String emailCert = emailCertCustomer.getText().toString();
                String sdi = sdiCustomer.getText().toString();
                String fiscalCode = fiscalCodeCustomer.getText().toString();
                String email = emailCustomer.getText().toString();
                String phone = phoneCustomer.getText().toString();
                String fax = faxCustomer.getText().toString();
                String note = noteCustomer.getText().toString();

                customer = new Customer(
                        Integer.parseInt(number),
                        Integer.parseInt(zipcode),
                        name,
                        address,
                        city,
                        country,
                        emailCert,
                        sdi,
                        fiscalCode,
                        email,
                        phone,
                        fax,
                        note
                );

                Call<Void> call = retrofitService.updateCustomer(apiKey, apiSecret, Integer.parseInt(customerID), customer);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call,
                                           @NonNull Response<Void> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            Log.e("rispostaUpdate", "risp: "+ response.isSuccessful());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Log.e("failureGallery", "failure: " + t);
                    }
                });

                FragmentManager fragmentManager = getParentFragmentManager();
                HomeFragment homeFragment = new HomeFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.update_fragment, homeFragment)
                        .commit();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
        // TODO: Use the ViewModel
    }

}