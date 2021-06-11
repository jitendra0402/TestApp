package com.net.testapp.data.db.repository;

import android.app.Application;

import com.net.testapp.data.db.AppDatabase;
import com.net.testapp.data.db.dao.UserDao;
import com.net.testapp.data.db.entity.UserEntity;

import java.util.List;

public class UserRepository {

    private UserDao dao;

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        dao = database.UserDao();
    }

    public void insert(UserEntity entity) {
        dao.insert(entity);
    }

    public void update(UserEntity entity) {
        dao.update(entity);
    }

    public void delete(UserEntity entity) {
        dao.delete(entity);
    }

    public List<UserEntity> getAll() {
        return dao.getAll();
    }
    public List<UserEntity> getUserByID(int data) {
        return dao.getUserByID(data);
    }

}
