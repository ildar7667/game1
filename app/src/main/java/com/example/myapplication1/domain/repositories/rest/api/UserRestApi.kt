package com.example.myapplication1.domain.repositories.rest.api

import com.example.myapplication1.Base.ABaseRestApi
import com.example.myapplication1.Base.IRestClient
import com.example.myapplication1.domain.di.models.User
import com.example.myapplication1.domain.di.modules.NetModule
import javax.inject.Inject
import javax.inject.Named
import com.example.myapplication1.domain.repositories.rest.service.IUserRestApiService


class UserRestApi : ABaseRestApi<IUserRestApiService> {


    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)

    fun registration(login: String, password: String)
            = service.registration(User(login = login, password = password))

    fun login(login: String, password: String)
            = service.login(User(login = login, password = password))

}