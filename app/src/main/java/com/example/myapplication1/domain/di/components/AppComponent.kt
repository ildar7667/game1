package com.example.myapplication1.domain.di.components

import com.example.myapplication1.domain.di.modules.NetModule
import com.example.myapplication1.presentation.Authorization.AuthorizationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [

    NetModule::class
])
interface AppComponent {

    fun inject(target: AuthorizationFragment)

}