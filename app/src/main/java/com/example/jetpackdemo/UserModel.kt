package com.example.jetpackdemo

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Entity(tableName = "user")
class UserModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "userId")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "gender")
    var gender: String = ""

    @ColumnInfo(name = "email_id")
    var emailId: String =""

    constructor(){}

    constructor(name: String,gender:String,emailId:String){
        this.name = name
        this.gender = gender
        this.emailId = emailId

    }
}




class UserViewModel(application: Application) : ViewModel() {

    private val userRepository:UserRepository

    init {
        val userDB = AppDataBase.getDatabase(application)
        val userDao = userDB.userDao()
        userRepository = UserRepository(userDao)
    }

    fun fetchAllUsers() : LiveData<List<UserModel>>{
        return  userRepository.fetchAllUsers
    }

    fun insertUser(user: UserModel){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(user = user)
        }
    }

    fun deleteUserById(id: Int) {
        viewModelScope.launch {
            userRepository.deleteCustomerById(id = id)
        }
    }
}

