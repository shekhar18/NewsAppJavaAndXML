package com.mahadiks.newsappjavaandxml.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mahadiks.newsappjavaandxml.data.local.database.User;
import com.mahadiks.newsappjavaandxml.data.remote.RemoteRepository;
import com.mahadiks.newsappjavaandxml.data.remote.models.News;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class MainViewModel extends ViewModel {


     public MutableLiveData<News> newsMutableLiveData = new MutableLiveData<>();
    private final RemoteRepository remoteRepository;
    private final MutableLiveData<Boolean> _isUserLogin = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();




    @Inject
    public MainViewModel(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
       // getNewsFromServer();
    }


    public void getNewsFromServer() {
        Disposable d = remoteRepository.getNews().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(news -> {
            Log.d("TAG", "" + news.totalResults);
            newsMutableLiveData.setValue(news);
        },throwable -> {
            // handle error properly here
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                int code = httpException.code();

                if (code == 426) {
                    // handle "Upgrade Required"
                    Log.e("API", "Server requires upgrade or API version change.");
                } else {
                    Log.e("API", "HTTP error code: " + code);
                }
            } else {
                Log.e("API", "Unexpected error : "+throwable.getMessage() );
            }
        });
        disposable.add(d);
    }


}
