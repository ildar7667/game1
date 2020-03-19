package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication1.presentation.starting.AuthorizationFragment


class Activity_Auth : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val ft =
            supportFragmentManager.beginTransaction()
         ft.add(R.id.container_auth, AuthorizationFragment())
         ft.commit()
    }
}
