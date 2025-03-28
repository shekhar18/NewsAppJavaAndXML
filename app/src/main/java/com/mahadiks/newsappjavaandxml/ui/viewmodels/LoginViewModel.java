package com.mahadiks.newsappjavaandxml.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.data.local.repo.UserLocalRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private  UserLocalRepository userLocalRepository;

    private final MutableLiveData<User> _userMutableLiveData = new MutableLiveData<>();
    public  LiveData<User> userLiveData = _userMutableLiveData;

    private final MutableLiveData<String> _userId = new MutableLiveData<>();
    public MutableLiveData<String> userId = _userId;

    private final MutableLiveData<String> _userPassword = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = _userPassword;

    private final MutableLiveData<String> _navigateToRegisterScreen = new MutableLiveData<>("");
    public LiveData<String> navigateToRegisterScreen = _navigateToRegisterScreen;

    private final MutableLiveData<Boolean> _navigationToMainScreen = new MutableLiveData<>(false);
    public LiveData<Boolean> navigateToMainScreen = _navigationToMainScreen;


    @Inject
    public LoginViewModel(UserLocalRepository userLocalRepository){
        this.userLocalRepository = userLocalRepository;
        User user = this.userLocalRepository.getUserWhichIsLoginAndSaveInPreference();
        if (user != null){
            _userMutableLiveData.setValue(user); ;
        }
    }



    private void checkAnyUserIsLogin() {
        _userMutableLiveData.setValue(userLocalRepository.getUserWhichIsLoginAndSaveInPreference());
    }


    public void navigateToRegisterScreen(){
        _navigateToRegisterScreen.setValue("");
    }

    public void loginUser(String userId, String password){
      Boolean isLogin = userLocalRepository.userGetLogin(userId,password);

        _navigationToMainScreen.setValue(isLogin);
    }

}
