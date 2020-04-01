package com.example.myapplication1.domain.repositories.models.rest

data class Token(
    val access: String,
    val refresh: String
)