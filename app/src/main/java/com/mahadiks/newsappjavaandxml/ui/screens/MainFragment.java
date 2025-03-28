package com.mahadiks.newsappjavaandxml.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mahadiks.newsappjavaandxml.R;
import com.mahadiks.newsappjavaandxml.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back press event


                Toast.makeText(getContext(), "Back Pressed", Toast.LENGTH_SHORT).show();
               requireActivity().finish();


            }
        });
    }
}