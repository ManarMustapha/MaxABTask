package com.example.maxabtask.ui.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.example.maxabtask.datamodel.MainRepository
import com.example.maxabtask.model.User
import com.example.maxabtask.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val mainRepository: MainRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private val _userState = MutableLiveData<ViewModelState<DiffUtil.DiffResult>>()
    val userState: LiveData<ViewModelState<DiffUtil.DiffResult>> get() = _userState

    var users: List<User> = emptyList()

    init {
        initUserObservable()
        fetchUsers()
    }

    fun fetchUsers() {
        disposable.add(
            mainRepository.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _userState.value = Loading(null) }
                .doFinally { _userState.value = StopLoading(null) }
                .subscribe({ }, { _userState.value = Failed(null, it) })
        )
    }

    private fun initUserObservable() {
        disposable.add(
            mainRepository.userObservable()
                .map { ArrayList<User>(it) }
                .doOnNext { it.addAll(0 , users) }
                .map { UserDiffUtil(users, it) }
                .map { Pair(DiffUtil.calculateDiff(it), it.getNewItems()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { users = it.second }
                .subscribe(
                    { _userState.value = Success(it.first) },
                    { _userState.value = Failed(null, it) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        mainRepository.tearDown()
        disposable.dispose()
    }
}