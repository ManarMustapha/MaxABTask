package com.example.maxabtask.datamodel.remote

import com.example.maxabtask.model.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IRemoteDataSource {

    @GET("/api/")
    fun fetchUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): Single<Results>
}