package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import com.example.myapplication1.domain.repositories.local.UserStorage
import com.example.myapplication1.presentation.App
import com.example.myapplication1.presentation.Authorization.AuthorizationFragment


class Activity_Auth : AppCompatActivity(), IActivity_Auth {

    companion object {

        private const val ARG_DROP_CREDENTIALS = "ARG_DROP_CREDENTIALS"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, IActivity_Auth::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ARG_DROP_CREDENTIALS, true)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState != null)
            return

        if (intent.getBooleanExtra(ARG_DROP_CREDENTIALS, false)) {
            UserStorage().dropCredentials()
            showAuth()
            return
        }
    }
/*
        val ft =
            supportFragmentManager.beginTransaction()
         ft.add(R.id.container_auth,
             AuthorizationFragment()
         )
         ft.commit()
    }*/

        override fun showAuth() {
            replace(AuthorizationFragment())
        }

    }

