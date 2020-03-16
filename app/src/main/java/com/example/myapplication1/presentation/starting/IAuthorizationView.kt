package com.example.myapplication1.presentation.starting
import com.arellomobile.mvp.MvpView

interface IAuthorizationView : MvpView {

    fun showError(message: String)
}