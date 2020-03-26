package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.btn_exit
import kotlinx.android.synthetic.main.activity_main.button_auth
import kotlinx.android.synthetic.main.activity_main.btn_1game
import com.example.myapplication1.presentation.App
import com.example.myapplication1.R
import com.example.myapplication1.ActivityAuth

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

        button_auth.setOnClickListener() {
           ActivityAuth.show()
         //   val intent = Intent(this, ActivityAuth::class.java)
        //  startActivity(intent)
        }

        btn_1game.setOnClickListener(){
      //   game1.show()
                        //   val intent = Intent(this, game1::class.java)
           // startActivity(intent)
        }

        btn_exit.setOnClickListener{
            System.exit(-1)
        }
    }


}




