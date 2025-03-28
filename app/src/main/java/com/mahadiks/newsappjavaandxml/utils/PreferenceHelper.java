package com.mahadiks.newsappjavaandxml.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mahadiks.newsappjavaandxml.data.local.database.User;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class PreferenceHelper {

    private static final String PREF_NAME = "NEWS_PREF";
    private final SharedPreferences preferences;
    @Inject
    public PreferenceHelper(@ApplicationContext Context context){
        preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }


    public void saveUser(String key, User user){
        preferences.edit().putString(key,new Gson().toJson(user)).apply();
    }

    public User getSaveUser(String key){
        return new Gson().fromJson(preferences.getString(key,""),User.class) ;
    }



}
