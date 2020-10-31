package com.example.maxabtask.datamodel.local

import com.example.maxabtask.datamodel.local.entities.UserEntity
import io.reactivex.Single

interface IAppDatabaseHelper {
    fun saveUsers(userEntities: List<UserEntity>)

    fun getCachedUsers(pageSize: Int, offset: Int): Single<List<UserEntity>>

    fun getTotalCount(): Single<Int>
}