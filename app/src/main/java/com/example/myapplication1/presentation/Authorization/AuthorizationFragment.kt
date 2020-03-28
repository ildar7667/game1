package com.example.myapplication1.presentation.Authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication1.R
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.Base.ABaseFragment
import com.example.myapplication1.MainActivity
import com.example.myapplication1.domain.di.components.DaggerAppComponent
import com.example.myapplication1.presentation.starting.IAuthorizationView
import kotlinx.android.synthetic.main.authorization.*
import javax.inject.Inject



class AuthorizationFragment: ABaseFragment(), IAuthorizationView{


        @Inject
        @InjectPresenter
        lateinit var presenter: AuthorizationPresenter

        @ProvidePresenter
        fun providePresenter() = presenter

        override fun inject() {
            DaggerAppComponent.create().inject(this)
        }

    override fun getViewId() = R.layout.authorization

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnLogin.setOnClickListener {

            val login = "${etLogin.text}" // "null"
            val password = "${etPassword.text}"

            if (login.isEmpty() || password.isEmpty()) {
                toast(R.string.error_login_passwd_undefined)
                return@setOnClickListener
            }

            presenter.auth(login, password)


          //  btnlogof.isEnabled = true
           // btnLogin.isEnabled = false
        }

        btnreg.setOnClickListener{
            presenter.registration("${etLogin.text}", "${etPassword.text}")
        }
      //  btnlogof.isEnabled  = false

      //  btnlogof.setOnClickListener{
          //  btnlogof.isEnabled = false
          //  btnLogin.isEnabled = true
      //  }

        btnlogof.setOnClickListener{
            MainActivity.show()
        }
    }


    override fun lock() {
      //  visibility(flBtnContainer)
    }

    override fun unlock() {
     //   visibility(flBtnContainer, false)
    }

    override fun onSuccess() {
        toast("SUCCESS")
        // Отправить на главную форму
    }


}