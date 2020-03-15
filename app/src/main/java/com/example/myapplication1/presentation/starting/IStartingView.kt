package com.example.myapplication1.presentation.starting
import com.arellomobile.mvp.MvpView

interface IStartingView : MvpView {

    fun showError(message: String)
}