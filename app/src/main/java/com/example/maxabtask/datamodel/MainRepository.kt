package com.example.maxabtask.datamodel

import com.example.maxabtask.datamodel.local.IAppDatabaseHelper
import com.example.maxabtask.datamodel.remote.IRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class MainRepository(val remoteDataSource: IRemoteDataSource,val localeHelper: IAppDatabaseHelper ,val disposable: CompositeDisposable) {

}