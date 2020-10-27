package com.example.maxabtask.datamodel.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maxabtask.datamodel.local.dao.UserDao
import com.example.maxabtask.datamodel.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}