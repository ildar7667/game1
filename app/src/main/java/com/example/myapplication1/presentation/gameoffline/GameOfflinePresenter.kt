package com.example.myapplication1.presentation.gameoffline

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.myapplication1.domain.repositories.GameRepository
import com.example.myapplication1.presentation.game.GameView
import com.example.myapplication1.presentation.game.ui.Ships
import javax.inject.Inject

@InjectViewState
class GameOfflinePresenter : MvpPresenter<IGameOfflineView> {


    @Inject
    lateinit var gameRepository: GameRepository

    @Inject
    constructor()

    fun gameoffline (str: String){

        gameRepository.gameofflineshow()



    }

    //var listshig = arrayListOf<Ships>()

}