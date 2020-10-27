package com.example.maxabtask

import android.app.Application
import com.example.maxabtask.di.*
import org.koin.android.ext.android.startKoin

class MaxABApplication() : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            applicationContext, modules = listOf(
                applicationModule,
                remoteDataSourceModule,
                dbModule,
                repositoryModule,
                mainModule
            )
        )
    }
}