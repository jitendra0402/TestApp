package com.net.testapp.data.db.dao;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.net.testapp.data.db.entity.UserEntity;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity entity);

    @Delete
    void delete(UserEntity entity);

    @Update
    void update(UserEntity entity);

    @Query("SELECT * FROM user_table")
    List<UserEntity> getAll();

    @Query("SELECT * FROM user_table where id = :data")
    List<UserEntity> getUserByID(int data);

}
