package com.example.maxabtask.util

open class ViewModelState<T>

data class Success<T>(val data: T) : ViewModelState<T>()

data class Loading<T>(val data: T? = null) : ViewModelState<T>()

data class StopLoading<T>(val data: T? = null) : ViewModelState<T>()

data class Failed<T>(val data: T?, val error: Throwable) : ViewModelState<T>()