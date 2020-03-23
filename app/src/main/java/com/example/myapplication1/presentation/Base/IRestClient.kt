package com.example.myapplication1.presentation.Base

interface IRestClient {
    fun <S> createService(serviceClass: Class<S>): S
    fun cancelAllRequests()
}