package com.mahadiks.newsappjavaandxml.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
   public MutableLiveData<String> stringMutableLiveData  = new MutableLiveData<String>("Click Me to See message");
    @Inject
    public MainViewModel() {

    }

    public String getStringMutableLiveDataValue(){
       return stringMutableLiveData.getValue();
   }
    public void setStringMutableLiveData(String message) {
        Log.d("TAG",message);
       this.stringMutableLiveData.setValue(message);
    }
}
