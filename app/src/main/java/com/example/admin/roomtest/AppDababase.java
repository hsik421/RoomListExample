package com.example.admin.roomtest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {User.class},version = 1)
abstract class AppDababase extends RoomDatabase{
    public abstract UserDao userDao();
}
