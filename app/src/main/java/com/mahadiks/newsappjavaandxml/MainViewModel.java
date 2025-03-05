package com.mahadiks.newsappjavaandxml;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
   public MutableLiveData<String> stringMutableLiveData  = new MutableLiveData<String>("Click Me to See message");


   public String getStringMutableLiveDataValue(){
       return stringMutableLiveData.getValue();
   }
    public void setStringMutableLiveData(String message) {
        Log.d("TAG",message);
       this.stringMutableLiveData.setValue(message);
    }
}
