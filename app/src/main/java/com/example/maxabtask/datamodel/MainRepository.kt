package com.example.maxabtask.datamodel

import android.os.CountDownTimer
import android.util.Log
import com.example.maxabtask.datamodel.local.IAppDatabaseHelper
import com.example.maxabtask.datamodel.remote.IRemoteDataSource
import com.example.maxabtask.model.User
import com.example.maxabtask.util.toUserEntities
import com.example.maxabtask.util.toUserModels
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MainRepository(
    private val remoteDataSource: IRemoteDataSource,
    private val localeHelper: IAppDatabaseHelper,
    private val disposable: CompositeDisposable
) {

    private val userSubject: BehaviorSubject<List<User>> =
        BehaviorSubject.createDefault(emptyList())

    private var counter: CountDownTimer? = null
    private var isRunning = false
    private var totalCachedPages = 1
    private var windowSize = 10
    private var offlinePage = 1
    private var onlinePage = 1

    init {
        getTotalCachedPage()
        initCounter()
    }

    private fun initCounter() {
        counter = object : CountDownTimer(1, 200) {
            override fun onFinish() {
                if (isRunning && totalCachedPages > offlinePage) getCachedUser()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
    }

    private fun getTotalCachedPage() {
        disposable.add(
            localeHelper.getTotalCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ totalCachedPages = it / 10 }, {})
        )
    }

    fun userObservable(): Observable<List<User>> = userSubject.hide()

    fun fetchUsers(): Completable {
        counter?.cancel()
        isRunning = true
        counter?.start()
        return remoteDataSource.fetchUsers(onlinePage, windowSize)
            .doOnSuccess { isRunning = false }
            .doOnSuccess { onlinePage++ }
            .doOnSuccess { offlinePage = onlinePage }
            .map { it.users }
            .doOnSuccess { userSubject.onNext(it) }
            .map { it.toUserEntities() }
            .doOnSuccess { localeHelper.saveUsers(it) }
            .ignoreElement()
    }

    private fun getCachedUser() {
        disposable.add(
            localeHelper.getCachedUsers(windowSize, offlinePage * windowSize)
                .map { it.toUserModels() }
                .doOnSuccess { offlinePage++ }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userSubject.onNext(it) }, { Log.e("Throwable", it.message ?: "") })
        )
    }

    fun tearDown() {
        disposable.dispose()
    }
}