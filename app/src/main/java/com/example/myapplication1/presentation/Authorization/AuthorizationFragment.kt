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
import com.example.myapplication1.presentation.starting.IAuthorizationView
import kotlinx.android.synthetic.main.authorization.*
import javax.inject.Inject
import com.example.myapplication1.domain.di.components.DaggerAppComponent


class AuthorizationFragment: ABaseFragment(), IAuthView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthorizationPresenter

    @ProvidePresenter
    fun providePresenter() =
        AuthorizationPresenter()

    override fun inject() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            presenter.registration("${etLogin.text}", "${etPassword.text}")
            btnlogof.isEnabled = true
            btnLogin.isEnabled = false
        }


        btnlogof.isEnabled  = false

        btnlogof.setOnClickListener{
            btnlogof.isEnabled = false
            btnLogin.isEnabled = true
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}