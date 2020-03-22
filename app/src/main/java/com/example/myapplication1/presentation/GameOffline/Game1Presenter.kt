package com.example.myapplication1.presentation.GameOffline

import android.widget.TableLayout
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.myapplication1.R
import com.example.myapplication1.presentation.starting.IGame1View
import com.example.myapplication1.repositories.UserRepository

@InjectViewState
class Game1Presenter : MvpPresenter<IGame1View>() {
    //    @Inject
    var userRepository: UserRepository = UserRepository()


    fun game1(a: String) {
        userRepository.game1({

            viewState.showError(it)

        }, a)


    }
}