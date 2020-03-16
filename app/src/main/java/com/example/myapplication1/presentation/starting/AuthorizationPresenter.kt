package com.example.myapplication1.presentation.starting

import com.example.myapplication1.repositories.UserRepository
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.InjectViewState

@InjectViewState
class AuthorizationPresenter : MvpPresenter<IAuthorizationView>() {

    //    @Inject
    var userRepository: UserRepository = UserRepository()

    fun registration(login: String, pass: String) {

        // показать диалог блокировки

        userRepository.registration({

            // дилог блокировки убрать

            // Тут будет ответ
            // Если ОК, то отправляем на гл. экран
            // иначе показываем сообщение об ошибки
            viewState.showError(it)

        }, login, pass)
    }
}