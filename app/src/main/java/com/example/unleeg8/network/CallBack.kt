package com.example.unleeg8.network

interface CallBack<T> {
    fun onSuccess(result: T)

    fun onFailed(exception:Exception)
}