package com.example.jetpackdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao{

    @Query("SELECT * From user")
    fun fetchAllUsers() : LiveData<List<UserModel>>

    @Insert()
    fun insertUser(user: UserModel)

    @Query("DELETE FROM user where userId = :id")
    fun deleteCustomerById(id: Int)

    @Query("DELETE FROM user")
    fun deleteAllCustomer()
}