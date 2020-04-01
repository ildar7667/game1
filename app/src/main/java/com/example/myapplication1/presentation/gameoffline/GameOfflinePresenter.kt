package com.example.myapplication1.presentation.gameoffline

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.myapplication1.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class GameOfflinePresenter : MvpPresenter<IGameOfflineView> {


    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun gameoffline (str: String){

       // gameofflineshow (str)
    }

}