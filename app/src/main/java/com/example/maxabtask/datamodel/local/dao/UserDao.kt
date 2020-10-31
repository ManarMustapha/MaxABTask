package com.example.maxabtask.datamodel.local.dao

import androidx.room.*
import com.example.maxabtask.datamodel.local.entities.UserEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {

    @Transaction
    fun removeAndAddUsers(trips: List<UserEntity>) {
        removeAllUsers()
        addUsers(trips)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(trips: List<UserEntity>)

    @Query("SELECT * FROM users LIMIT :pageSize OFFSET :offset")
    fun fetchUsers(pageSize: Int, offset: Int): Single<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM users")
    fun getTotalCount(): Single<Int>

    @Query("DELETE FROM users")
    fun removeAllUsers()
}