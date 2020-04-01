package com.example.myapplication1.presentation.credentials.starting

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.myapplication1.domain.repositories.UserRepository
import com.example.myapplication1.presentation.credentials.starting.IStartingView
import javax.inject.Inject

@InjectViewState
class StartingPresenter : MvpPresenter<IStartingView>{

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun game1(a: String) {
        userRepository.game1({

            viewState.showError(it)

        }, a)
    }
}