package com.example.myapplication1.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.R
import com.example.myapplication1.presentation.App
import com.example.myapplication1.presentation.credentials.ActivityAuth
import com.example.myapplication1.presentation.gameoffline.ActivityGameOffline
import com.example.myapplication1.presentation.gameonline.ActivityOnline
import com.example.myapplication1.presentation.gametwoplayer.ActivityGameTwoPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null)
            return


        btn_2game.setOnClickListener() {
            ActivityGameTwoPlayer.show()

        }

        button_auth.setOnClickListener() {
           ActivityAuth.show()

        }

        btngameoffline.setOnClickListener(){

            ActivityGameOffline.show()
        }

        btn_lan.setOnClickListener() {

               ActivityOnline.show()
        }

        btn_exit.setOnClickListener{
            System.exit(-1)
        }
    }


}




