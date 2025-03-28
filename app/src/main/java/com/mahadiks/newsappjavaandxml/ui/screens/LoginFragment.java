package com.mahadiks.newsappjavaandxml.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mahadiks.newsappjavaandxml.R;
import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.databinding.FragmentLoginBinding;
import com.mahadiks.newsappjavaandxml.ui.viewmodels.LoginViewModel;


public class LoginFragment extends Fragment {


    LoginViewModel viewModel;

    FragmentLoginBinding binding = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //getting viewmodel in fragment
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();

        viewModel.userLiveData.observe(this, loginUser -> {
            if (loginUser.IsActive) {
                //navigate to mainScreen
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_mainFragment);
            }
        });

        //here is i use live data to for navigation
        /*viewModel.navigateToRegisterScreen.observe(this, s -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_registerFragment);
        });*/


        //here we  using the binding and click listener.
        binding.textView3.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_registerFragment);
        });

        viewModel.navigateToMainScreen.observe(this, aBoolean -> {
            if (aBoolean) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_mainFragment);
            }
        });
    }
}