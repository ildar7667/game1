package com.example.myapplication1.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.R
import com.example.myapplication1.presentation.App
import com.example.myapplication1.presentation.credentials.ActivityAuth
import com.example.myapplication1.presentation.gameoffline.ActivityGameOffline
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

        /*   запуск через фрагмент

      val ft =
                supportFragmentManager.beginTransaction()

            ft.add(R.id.container, StartingFragment())
            ft.addToBackStack(null)
        ft.commit()*/

        //button_auth.setOnClickListener() {
        // ft.add(R.id.container, AuthorizationFragment())
        // ft.commit()
        // }


        // }

        // supportFragmentManager.beginTransaction()
        //    .replace(R.id.container, StartingFragment())
        //   .commit()

        /*запуск через новое активити*/

        btn_2game.setOnClickListener() {
            ActivityGameTwoPlayer.show()

        }

        button_auth.setOnClickListener() {
           ActivityAuth.show()
         //   val intent = Intent(this, ActivityAuth::class.java)
        //  startActivity(intent)
        }

        btngameoffline.setOnClickListener(){
      //   game1.show()
                        //   val intent = Intent(this, game1::class.java)
           // startActivity(intent)
            ActivityGameOffline.show()
        }

        btn_exit.setOnClickListener{
            System.exit(-1)
        }
    }


}




