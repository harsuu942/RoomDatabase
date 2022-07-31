package com.example.jetpackdemo

import android.app.Application
import androidx.lifecycle.LiveData

class UserRepository(private val userDao:UserDao) {

    val fetchAllUsers: LiveData<List<UserModel>> = userDao.fetchAllUsers()

    fun insertUser(user: UserModel) {
        userDao.insertUser(user)
    }

    fun deleteCustomerById(id: Int) {
        userDao.deleteCustomerById(id)
    }

    fun deleteAllCustomer() {
        userDao.deleteAllCustomer()
    }
}