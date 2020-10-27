package com.example.maxabtask.datamodel.local

import com.example.maxabtask.datamodel.local.entities.UserEntity

interface IAppDatabaseHelper {
    fun insertUsers(userEntities: List<UserEntity>)
}