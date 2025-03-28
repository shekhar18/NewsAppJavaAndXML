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
import com.mahadiks.newsappjavaandxml.databinding.FragmentRegisterBinding;
import com.mahadiks.newsappjavaandxml.ui.viewmodels.RegistrationViewModel;

public class RegisterFragment extends Fragment {


    private FragmentRegisterBinding fragmentRegisterBinding = null;
    private RegistrationViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater);
        viewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        fragmentRegisterBinding.setViewModel(viewModel);
        fragmentRegisterBinding.setLifecycleOwner(this);
        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.userIsCreated.observe(this, aLong -> {
            if (aLong != -1L) {
                Toast.makeText(getContext(), "User is created ("+aLong+")", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(fragmentRegisterBinding.getRoot()).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        fragmentRegisterBinding.backButton.setOnClickListener(view -> {
            Navigation.findNavController(fragmentRegisterBinding.getRoot()).navigate(R.id.action_registerFragment_to_loginFragment);
        });
    }
}