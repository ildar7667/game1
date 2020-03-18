package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.presentation.starting.StartingFragment
import com.example.myapplication1.presentation.starting.StartingPresenter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         val ft =
                supportFragmentManager.beginTransaction()

            ft.add(R.id.container, StartingFragment())
            ft.addToBackStack(null)
            ft.commit()

        // }

        // supportFragmentManager.beginTransaction()
        //    .replace(R.id.container, StartingFragment())
        //   .commit()

    }

  //  button_auth.setOnClickListener() {

   // }
   /* button_auth.setOnClickListener {
        val intent = Intent(context, Activity_Auth::class.java)
        startActivity(intent) }
*/

}




