package com.example.myapplication1.domain.repositories

import com.example.myapplication1.Base.SubRX
import com.example.myapplication1.Base.standardSubscribeIO
import com.example.myapplication1.domain.repositories.models.rest.Token
import com.example.myapplication1.domain.repositories.models.rest.User
import com.example.myapplication1.domain.repositories.local.UserStorage
import com.example.myapplication1.domain.repositories.rest.api.UserRestApi
import javax.inject.Inject


class UserRepository {

    fun game1(subscriber: (String) -> Unit, a: String) {
        subscriber.invoke(a)
        //showError ("Одиночная игра")
    }

    private val storage: UserStorage
    private val rest: UserRestApi

    @Inject
    constructor(storage: UserStorage, rest: UserRestApi) {
        this.storage = storage
        this.rest = rest
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {

        rest.registration(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun login(observer: SubRX<User>, login: String, pass: String) {

        rest.login(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun getUser() = storage.user

    fun refreshToken(token: Token): Token {
        TODO("Not yet implemented")
    }

    fun gameofflineshow () {

    }
}
