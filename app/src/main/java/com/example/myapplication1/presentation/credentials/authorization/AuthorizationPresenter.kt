package com.example.myapplication1.presentation.credentials.authorization

import com.example.myapplication1.domain.repositories.UserRepository
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.InjectViewState
import com.example.myapplication1.Base.SubRX
import com.example.myapplication1.presentation.main.MainActivity
import javax.inject.Inject



@InjectViewState
class AuthorizationPresenter : MvpPresenter<IAuthorizationView> {


    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    constructor()

    fun auth(login: String, password: String) {

        userRepository.login(SubRX { _, e ->

            if (e != null) {
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }


            MainActivity.show()
        }, login, password)
    }

    fun registration(login: String, pass: String) {

        viewState.lock()
        userRepository.registration(SubRX { _, e ->
            viewState.unlock()

            if (e != null) {
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }

            MainActivity.show()

        }, login, pass)
    }
}