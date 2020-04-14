package com.example.myapplication1.presentation.game.model

data class GameState (
    val status: Int,
    val game: List<Int>,
    val players: List<GamePlayer>,
    val winner: GamePlayer?
)