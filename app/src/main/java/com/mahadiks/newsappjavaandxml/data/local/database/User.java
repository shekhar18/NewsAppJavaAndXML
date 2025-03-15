package com.mahadiks.newsappjavaandxml.data.local.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table", indices = {@Index(value = {"contact_number","email"},unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name ="first_name" )
    public String firstName;

    @ColumnInfo(name="last_name")
    public String lastName;

    @ColumnInfo(name="email")
    public String userEmail;

    @ColumnInfo(name="contact_number")
    public String userContactNumber;

    @ColumnInfo(name="user_password")
    public String userPassword;

    @ColumnInfo(name="user_avatar")
    public String userAvatar;

    @ColumnInfo(name = "active")
    public Boolean IsActive;

}
