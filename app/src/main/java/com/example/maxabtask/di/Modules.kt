package com.example.maxabtask.di

import androidx.room.Room
import com.example.maxabtask.datamodel.MainRepository
import com.example.maxabtask.datamodel.local.AppDatabase
import com.example.maxabtask.datamodel.local.AppDatabaseHelper
import com.example.maxabtask.datamodel.local.IAppDatabaseHelper
import com.example.maxabtask.datamodel.remote.IRemoteDataSource
import com.example.maxabtask.ui.mainscreen.MainViewModel
import com.example.maxabtask.util.Constants
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    factory { CompositeDisposable() }
}

val remoteDataSourceModule = module {
    single { RetrofitModule.createOKHttpClient() }
    single { RetrofitModule.createWebService<IRemoteDataSource>(get(), Constants.BASE_URL) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "MaxAB")
            .build()
    }
    single<IAppDatabaseHelper> { AppDatabaseHelper(get()) }
}

val repositoryModule = module {
    factory { MainRepository(get(), get(), get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get(), get()) }
}