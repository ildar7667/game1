package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.btn_exit
import kotlinx.android.synthetic.main.activity_main.button_auth
import kotlinx.android.synthetic.main.activity_main.btn_1game


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        button_auth.setOnClickListener() {
            val intent = Intent(this, Activity_Auth::class.java)
            startActivity(intent)
        }

        btn_1game.setOnClickListener(){
            val intent = Intent(this, game1::class.java)
            startActivity(intent)
        }

        btn_exit.setOnClickListener{
            System.exit(-1)
        }
    }


}




