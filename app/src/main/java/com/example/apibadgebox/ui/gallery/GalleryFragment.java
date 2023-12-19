package com.example.apibadgebox.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.data.service.CustomerService;
import com.example.apibadgebox.databinding.FragmentGalleryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private String baseUrl = "https://www.badgebox.com/api/v1/";

    private String apiKey = "5277ff227ef6b66797d4c934025a2f8a";
    private String apiSecret = "02a7cef518d041d9732e05fc0b5e4430";
    private Customer customer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.titleGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build();

        CustomerService retrofitService = retrofit.create(CustomerService.class);

        TextView numberCustomer = binding.numberGallery;
        TextView zipcodeCustomer = binding.zipcodeGallery;
        TextView nameCustomer = binding.nameGallery;
        TextView addressCustomer = binding.addressGallery;
        TextView cityCustomer = binding.cityGallery;
        TextView countryCustomer = binding.countryGallery;
        TextView emailCertCustomer = binding.pecGallery;
        TextView sdiCustomer = binding.sdiGallery;
        TextView fiscalCodeCustomer = binding.cfGallery;
        TextView emailCustomer = binding.emailGallery;
        TextView phoneCustomer = binding.phoneGallery;
        TextView faxCustomer = binding.faxGallery;
        TextView noteCustomer = binding.noteGallery;

        Button button = binding.buttonGallery;
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

                /*customer = new Customer(
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
                );*/
                customer = new Customer(
                        55555,
                        88888888,
                        "ROSE",
                        "VIA ROSE",
                        "ROSE",
                        "ROSELAND",
                        "giova@pec.it",
                        "wehiy2fj12ui",
                        "28",
                        "ROSE@nni.com",
                        "123456789",
                        "GIO28",
                        "Mi crasha tutto"
                );

                Call<Customer> call = retrofitService.postCustomer(apiKey, apiSecret, customer);
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(@NonNull Call<Customer> call,
                                           @NonNull Response<Customer> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            Log.e("risposta", "risp: "+ response.isSuccessful());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                        Log.e("failureGallery", "failure: " + t);
                    }
                });


            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}