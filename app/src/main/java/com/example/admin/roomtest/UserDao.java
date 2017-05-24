package com.example.admin.roomtest;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by admin on 2017-05-23.
 */
@Dao
public interface UserDao {
    @Query("select * from user")
    List<User> getAll();

    @Insert
    void insertUser(User user);

    @Delete
    void delete(User user);

//    @Query("select * from where id = :id")
//    User selectUser(String id);
}
