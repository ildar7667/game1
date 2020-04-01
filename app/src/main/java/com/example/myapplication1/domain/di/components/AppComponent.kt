package com.example.myapplication1.domain.di.components

import com.example.myapplication1.domain.di.modules.NetModule
import com.example.myapplication1.presentation.credentials.authorization.AuthorizationFragment
import com.example.myapplication1.presentation.gameoffline.GameOfflineFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [

    NetModule::class
])
interface AppComponent {

    fun inject(target: AuthorizationFragment)
    fun injectgame(target: GameOfflineFragment)

}