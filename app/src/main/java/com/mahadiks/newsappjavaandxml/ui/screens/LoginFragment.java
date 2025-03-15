package com.mahadiks.newsappjavaandxml.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mahadiks.newsappjavaandxml.R;
import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.databinding.FragmentLoginBinding;
import com.mahadiks.newsappjavaandxml.ui.viewmodels.MainViewModel;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    MainViewModel viewModel;

    FragmentLoginBinding binding = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //getting viewmodel in fragment
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        User user = new User();
        user.firstName = "shekhar";
        user.lastName = "mahadik";
        user.userContactNumber = "7757925001";
        user.userEmail = "madahiks24@gmail.com";
        user.userPassword = "123456";
        user.IsActive = true;
        user.userAvatar = "fhgdfghdfghdfghdfghdfg";
        viewModel.createUser(user);
        //here we checking that users is present in the database
        //if not found then we show login screen else show user selection list.

        viewModel.checkUserIsPresentOrNot();


    }


    @Override
    public void onResume() {
        super.onResume();

        viewModel.isUserLogin.observe(this, aBoolean -> {
            if (aBoolean) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_mainFragment);
            }else{
                Toast.makeText(getActivity(), "password or user id is wrong..!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}