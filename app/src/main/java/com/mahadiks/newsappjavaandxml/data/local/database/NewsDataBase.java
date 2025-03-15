package com.mahadiks.newsappjavaandxml.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class NewsDataBase extends RoomDatabase {
    private static volatile NewsDataBase INSTANCE;
    public abstract NewsDao newsDao();
    public static NewsDataBase getDataBase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsDataBase.class, "news_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }



}
