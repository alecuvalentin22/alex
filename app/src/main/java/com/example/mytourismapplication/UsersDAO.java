package com.example.mytourismapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDAO {

    @Insert
    long insert(User user);

    @Query("select * from users")
    List<User> getAll();

    @Query("delete from users")
    void deleteAll();

    @Delete
    void delete(User user);
}
