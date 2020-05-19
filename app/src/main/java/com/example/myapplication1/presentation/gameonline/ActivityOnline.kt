package com.example.myapplication1.presentation.gameonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication1.Base.ABaseActivity
import com.example.myapplication1.R
import com.example.myapplication1.presentation.App
import com.example.myapplication1.presentation.gametwoplayer.ActivityGameTwoPlayer
import com.example.myapplication1.presentation.gametwoplayer.IActivityGameTwoPlayer

class ActivityOnline : ABaseActivity(), IActivityOnline {

    companion object {

        private const val ARG_DROP_CREDENTIALS = "ARG_DROP_CREDENTIALS"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, ActivityOnline::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK // or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ARG_DROP_CREDENTIALS, true)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online)
    }
}
