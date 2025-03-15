package com.mahadiks.newsappjavaandxml.data.local.repo;

import android.util.Log;

import com.mahadiks.newsappjavaandxml.data.NewsRepository;
import com.mahadiks.newsappjavaandxml.data.local.database.NewsDao;
import com.mahadiks.newsappjavaandxml.data.local.database.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.FutureTask;

import javax.inject.Inject;

public class UserLocalRepository implements NewsRepository {
    static String TAG = "REPO";
    private final NewsDao newsDao;
    private Boolean taskIsOnGoing = false;
    @Inject
    UserLocalRepository(NewsDao newsDao) {
        this.newsDao = newsDao;
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

    public Boolean userGetLogin(String mobileNumberOrEmailId,String userPassword){
        FutureTask<User> loginTask = new FutureTask<>(()->newsDao.getUserLogin(mobileNumberOrEmailId, userPassword));
        try {
            new Thread(loginTask).start();
           User user = loginTask.get();
            if(user != null){
                return true;
            }
        }catch (Exception e){
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
        return false;
    }


    public void createUser(User user) {
        try {
            if (!taskIsOnGoing) {
                taskIsOnGoing = true;
                new Thread(() -> newsDao.insertUser(user)).start();
                taskIsOnGoing = false;
            }
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }
}
