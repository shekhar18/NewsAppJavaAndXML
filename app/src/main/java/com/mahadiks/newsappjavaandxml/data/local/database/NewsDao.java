package com.mahadiks.newsappjavaandxml.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertUser(User user);
    @Query("SELECT * from users_table ORDER By first_name Asc")
    List<User> getUsers();

    @Query("SELECT * FROM users_table WHERE contact_number = :userNumber AND user_password = :userPassword")
    User getUserLogin(String userNumber, String userPassword);

    @Query("DELETE FROM users_table WHERE id = :userId AND contact_number = :phoneNumber")
    int getUsers(int userId,String phoneNumber);

    @Query("SELECT * FROM users_table WHERE active == true")
    User getAnyLoginUse();

    @Delete
    int getDeleteAll(User user);
}
