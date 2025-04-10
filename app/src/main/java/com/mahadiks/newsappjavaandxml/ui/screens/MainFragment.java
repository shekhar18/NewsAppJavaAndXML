package com.mahadiks.newsappjavaandxml.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mahadiks.newsappjavaandxml.databinding.FragmentMainBinding;
import com.mahadiks.newsappjavaandxml.ui.adapters.NewsAdapter;
import com.mahadiks.newsappjavaandxml.ui.viewmodels.MainViewModel;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding = null;

    private MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.setVm(mainViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();


        mainViewModel.getNewsFromServer();

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


        mainViewModel.newsMutableLiveData.observe(this,news -> {
            if(!news.articles.isEmpty()){
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
                NewsAdapter newsAdapter = new NewsAdapter(news.articles,requireContext());
                binding.rvNewsList.setLayoutManager(linearLayoutManager);
                binding.rvNewsList.setAdapter(newsAdapter);
            }
        });

    }
}