package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.presentation.starting.AuthorizationFragment
import com.example.myapplication1.presentation.starting.IStartingView
import com.example.myapplication1.presentation.starting.StartingFragment
import com.example.myapplication1.presentation.starting.StartingPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_exit
import kotlinx.android.synthetic.main.activity_main.button_auth
import kotlinx.android.synthetic.main.starting.*


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

        btn_exit.setOnClickListener{
            System.exit(-1)
        }
    }

  //  button_auth.setOnClickListener() {

   // }
   /* button_auth.setOnClickListener {
        val intent = Intent(context, Activity_Auth::class.java)
        startActivity(intent) }
*/



}




