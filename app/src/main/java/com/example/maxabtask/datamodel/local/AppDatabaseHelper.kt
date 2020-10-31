package com.example.maxabtask.datamodel.local

import com.example.maxabtask.datamodel.local.entities.UserEntity

class AppDatabaseHelper(private val appDatabase: AppDatabase) : IAppDatabaseHelper {

    override fun saveUsers(userEntities: List<UserEntity>) =
        appDatabase.userDao().addUsers(userEntities)

    override fun getCachedUsers(pageSize: Int, offset: Int) =
        appDatabase.userDao().fetchUsers(pageSize, offset)

    override fun getTotalCount() =
        appDatabase.userDao().getTotalCount()
}