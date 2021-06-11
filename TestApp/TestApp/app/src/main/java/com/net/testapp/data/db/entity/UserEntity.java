package com.net.testapp.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "user_table")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String emailid;
    public String fname;
    public int gender;
    public String password;

    public UserEntity(String emailid, String fname, int gender, String password) {
        this.emailid = emailid;
        this.fname = fname;
        this.gender = gender;
        this.password = password;
    }
}
