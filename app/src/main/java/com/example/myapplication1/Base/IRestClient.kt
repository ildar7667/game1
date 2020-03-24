package com.example.myapplication1.Base

interface IRestClient {
    fun <S> createService(serviceClass: Class<S>): S
    fun cancelAllRequests()
}