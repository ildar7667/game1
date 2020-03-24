package com.example.myapplication1.domain.di.models

data class User(
    val id: Int? = null,
    val login: String,
    val password: String,
    val avatar_url: String? = null,
    val token: Token? = null
)