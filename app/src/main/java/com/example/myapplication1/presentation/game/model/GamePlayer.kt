package com.example.myapplication1.presentation.game.model

data class GamePlayer(

    val userId: Int,
    val userLogin: String,
    val action: Boolean,
    val actionType: Int,
    val winCounter: Int,
    val isOnline: Boolean,
    val isReady: Boolean
)