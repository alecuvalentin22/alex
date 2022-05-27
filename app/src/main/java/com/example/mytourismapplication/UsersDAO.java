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
    List<User> getAllUsers();

    @Query("delete from users")
    void deleteAll();

    @Query("SELECT * FROM users WHERE name = :name")
    User getByName(String name);

    @Query("UPDATE users SET name=:givenName WHERE name = :existingName")
    void update(String givenName, String existingName);

    @Delete
    void delete(User user);
}
