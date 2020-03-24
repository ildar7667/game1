package com.example.myapplication1.domain.repositories.rest.service

import com.example.myapplication1.domain.di.models.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface IUserRestApiService {


     // Регистрация нового пользователя

    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>

     // Авторизация пользователя

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>


//       Получить токен : grantType="refresh_token" и refresh_token

}