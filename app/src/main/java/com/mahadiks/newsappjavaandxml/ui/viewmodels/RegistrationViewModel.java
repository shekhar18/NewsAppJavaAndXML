package com.mahadiks.newsappjavaandxml.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.data.local.repo.UserLocalRepository;
import com.mahadiks.newsappjavaandxml.utils.UserValidationUtil;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegistrationViewModel extends ViewModel {



    private UserLocalRepository userLocalRepository;

    public MutableLiveData<String> firstName = new MutableLiveData<>("");
    public MutableLiveData<String> lastName = new MutableLiveData<>("");
    public MutableLiveData<String> emailId = new MutableLiveData<>("");
    public MutableLiveData<String> contactNumber = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>("");

    public MutableLiveData<Long> userIsCreated = new MutableLiveData<>();
    @Inject
    public RegistrationViewModel(UserLocalRepository userLocalRepository){
        this.userLocalRepository = userLocalRepository;
    }


    public void createUser(){
        if(UserValidationUtil.isValidUser(firstName.getValue(),lastName.getValue(),emailId.getValue(),contactNumber.getValue(),password.getValue(),confirmPassword.getValue())){
            User user = new User();
            user.firstName=firstName.getValue();
            user.lastName = lastName.getValue();
            user.userEmail = emailId.getValue();
            user.userContactNumber = contactNumber.getValue();
            user.userPassword = password.getValue();
            user.IsActive = true;
            user.userAvatar = "";

            userIsCreated.setValue(userLocalRepository.createUser(user));
        }
    }






}
