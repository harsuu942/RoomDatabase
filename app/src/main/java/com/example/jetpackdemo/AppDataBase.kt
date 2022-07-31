package com.example.jetpackdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object{
        private var INSTANCE:AppDataBase? = null

        fun getDatabase(context:Context):AppDataBase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "userDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}