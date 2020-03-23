package com.example.myapplication1.presentation.Base

import com.arellomobile.mvp.MvpView

interface IBaseView: MvpView {
    fun lock()
    fun unlock()
    fun onSuccess(message: String)
    fun onError(message: String)
}