package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication1.presentation.starting.AuthorizationFragment

import com.example.myapplication1.presentation.starting.Game1Fragment


class game1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game1)

        val ft =
            supportFragmentManager.beginTransaction()
        ft.add(R.id.container_game1, Game1Fragment())
        ft.commit()
    }
}
