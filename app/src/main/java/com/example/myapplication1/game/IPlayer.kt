package com.example.myapplication1.game

interface IPlayer {

    fun start()
    fun ready()
    fun cell(value: Int)
    fun exit()
    fun render()
}