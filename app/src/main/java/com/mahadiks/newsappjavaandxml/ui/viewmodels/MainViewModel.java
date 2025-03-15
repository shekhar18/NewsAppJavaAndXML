package com.mahadiks.newsappjavaandxml.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.data.local.repo.UserLocalRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {


    private final UserLocalRepository userLocalRepository;
    private final MutableLiveData<List<User>> usersIsPresentList = new MutableLiveData<>();
    private MutableLiveData<Boolean> _isUserLogin = new MutableLiveData<>();
    public LiveData<Boolean> isUserLogin = _isUserLogin;
    public MutableLiveData<String> userId = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();


    @Inject
    public MainViewModel(UserLocalRepository userLocalRepository) {
        this.userLocalRepository = userLocalRepository;
    }

    public void checkUserIsPresentOrNot() {
        usersIsPresentList.postValue(userLocalRepository.checkUserIsPresent());
    }

    public void createUser(User user) {
        userLocalRepository.createUser(user);
    }


    public void userGetLogin(String mobileNumberOrEmailId,String userPassword){

        _isUserLogin.setValue(userLocalRepository.userGetLogin(mobileNumberOrEmailId,userPassword));


    }



}
