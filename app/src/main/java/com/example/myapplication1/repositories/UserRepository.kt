package com.example.myapplication1.repositories


class UserRepository {
    fun registration(subscriber: (String) -> Unit, login: String, pass: String) {
        subscriber.invoke("$login : $pass")
    }

    fun game1(){
       // showError ("Одиночная игра")
    }

}