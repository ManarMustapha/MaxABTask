package com.example.maxabtask.datamodel.local

import com.example.maxabtask.datamodel.local.entities.UserEntity

class AppDatabaseHelper(private val appDatabase: AppDatabase) : IAppDatabaseHelper {

    override fun insertUsers(userEntities: List<UserEntity>) {
        appDatabase.userDao().addUsers(userEntities)
    }

}