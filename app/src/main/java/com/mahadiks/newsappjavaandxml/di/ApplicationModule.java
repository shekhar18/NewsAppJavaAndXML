package com.mahadiks.newsappjavaandxml.di;

import android.content.Context;

import com.mahadiks.newsappjavaandxml.data.NewsApiService;
import com.mahadiks.newsappjavaandxml.data.local.database.NewsDao;
import com.mahadiks.newsappjavaandxml.data.local.database.NewsDataBase;
import com.mahadiks.newsappjavaandxml.utils.Constants;
import com.mahadiks.newsappjavaandxml.utils.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Provides
    @Singleton
    NewsDataBase getProvideNewsDataBase(@ApplicationContext Context context){
        return NewsDataBase.getDataBase(context);
    }

    @Provides
    @Singleton
    public static PreferenceHelper providePreferenceHelper(@ApplicationContext Context context) {
        return new PreferenceHelper(context);
    }

    @Provides
    @Singleton
    NewsDao getProviderNewsDao(NewsDataBase newsDataBase){
        return newsDataBase.newsDao();
    }

    @Provides
    @Singleton
    Retrofit getProviderRetrofit(){
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
    }

    @Provides
    @Singleton
    NewsApiService getProviderNewsApiService(Retrofit retrofit){
        return retrofit.create(NewsApiService.class);
    }

}
