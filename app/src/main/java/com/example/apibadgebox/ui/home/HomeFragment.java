package com.example.apibadgebox.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apibadgebox.data.RetrofitIstance;
import com.example.apibadgebox.data.adapter.CustomersAdapter;
import com.example.apibadgebox.data.interfacce.CustomersCallback;
import com.example.apibadgebox.data.model.Customer;
import com.example.apibadgebox.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CustomersCallback {

    private FragmentHomeBinding binding;
    public List<Customer> data;
    private CustomersAdapter customerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RecyclerView recyclerView = binding.reciclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        customerAdapter = new CustomersAdapter(data, requireContext(), getParentFragmentManager());

        recyclerView.setAdapter(customerAdapter);
        RetrofitIstance retrofitIstance = new RetrofitIstance();
        retrofitIstance.readCustomers(customerAdapter, this);

        //Log.e("datio","datio: " + data);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCustomerDataReceived(List<Customer> customers) {
        data.clear();
        data.addAll(customers);
        customerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCustomerDataFailed(Throwable t) {
        Log.e("dataFAIL", "error: " + t);
    }
}