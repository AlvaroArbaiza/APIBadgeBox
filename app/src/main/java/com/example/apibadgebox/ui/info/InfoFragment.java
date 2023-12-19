package com.example.apibadgebox.ui.info;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.apibadgebox.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info, container, false);
        Uri uri = Uri.parse("https://www.gflash.it/android/video.mp4");
        VideoView video = v.findViewById(R.id.videoView);

        video.setVideoURI(uri);
        video.start();

        CalendarView calendarView = v.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
            }
        });

        RadioGroup radiogroup = v.findViewById(R.id.radioGroup);
        RadioButton radioButton1 = v.findViewById(R.id.radioButton);
        RadioButton radioButton2 = v.findViewById(R.id.radioButton2);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton1.isChecked();
                radioButton2.isChecked();
                if(radioButton1.isChecked())
                {
                    Log.e("radio", "scelta 1 " + radioButton1.isChecked());

                }else if(radioButton2.isChecked()){
                    Log.e("radio", "scelta 2 " + radioButton2.isChecked());

                }
            }
        });

        return v;
    }
}