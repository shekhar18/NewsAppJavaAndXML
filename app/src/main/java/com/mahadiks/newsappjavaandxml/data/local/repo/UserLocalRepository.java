package com.mahadiks.newsappjavaandxml.data.local.repo;

import android.util.Log;

import com.mahadiks.newsappjavaandxml.data.NewsRepository;
import com.mahadiks.newsappjavaandxml.data.local.database.NewsDao;
import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.utils.PreferenceHelper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.FutureTask;

import javax.inject.Inject;

public class UserLocalRepository implements NewsRepository {
    static String TAG = "REPO";
    private final NewsDao newsDao;
    private final PreferenceHelper helper;
    private Boolean taskIsOnGoing = false;

    @Inject
    UserLocalRepository(NewsDao newsDao,PreferenceHelper preferenceHelper) {
        this.newsDao = newsDao;
        this.helper = preferenceHelper;
    }


    public void getLoginUser() {
        FutureTask<User> futureTask = new FutureTask<>(newsDao::getAnyLoginUse);
        try {
            new Thread(futureTask).start();
            User user =  futureTask.get();
            helper.saveUser("USER",user);
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public User getUserWhichIsLoginAndSaveInPreference(){
        return helper.getSaveUser("USER");
    }


    public List<User> checkUserIsPresent() {
        List<User> userList = null;
        FutureTask<List<User>> futureTask = new FutureTask<>(newsDao::getUsers);
        try {
            new Thread(futureTask).start();
            return futureTask.get();
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
        return Collections.emptyList();
    }

    public Boolean userGetLogin(String mobileNumberOrEmailId, String userPassword) {
        FutureTask<User> loginTask = new FutureTask<>(() -> newsDao.getUserLogin(mobileNumberOrEmailId, userPassword));
        try {
            new Thread(loginTask).start();
            User user = loginTask.get();
            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
        return false;
    }


    public Long createUser(User user) {
        FutureTask<Long> createUser = new FutureTask<>(() -> newsDao.insertUser(user));
        try {
           new Thread(createUser).start();
           return createUser.get();
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
        return -1L;
    }
}
