package com.net.testapp.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.net.testapp.data.db.entity.UserEntity;
import com.net.testapp.data.db.repository.UserRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private UserRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insertUser(UserEntity entity) { repository.insert(entity);}
    public void deleteUser(UserEntity entity) {
        repository.delete(entity);
    }

    public List<UserEntity> getAllUsers() {
        return repository.getAll();
    }

    public List<UserEntity> getUserbyUserId(int userid) {
        return repository.getUserByID(userid);
    }

}
