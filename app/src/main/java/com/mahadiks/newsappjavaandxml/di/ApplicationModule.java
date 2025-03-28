package com.mahadiks.newsappjavaandxml.di;

import android.content.Context;

import com.mahadiks.newsappjavaandxml.data.local.database.NewsDao;
import com.mahadiks.newsappjavaandxml.data.local.database.NewsDataBase;
import com.mahadiks.newsappjavaandxml.utils.PreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

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

}
