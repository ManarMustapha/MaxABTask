package com.example.maxabtask.ui.mainscreen

import androidx.lifecycle.ViewModel
import com.example.maxabtask.datamodel.MainRepository
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val mainRepository: MainRepository,
    val disposable: CompositeDisposable
) : ViewModel() {


}