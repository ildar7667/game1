package com.example.myapplication1.presentation.starting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.R
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.starting.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.MainActivity


class StartingFragment : MvpAppCompatFragment(), IStartingView {

    @InjectPresenter
    lateinit var presenter: StartingPresenter

    @ProvidePresenter
    fun providePresenter() = StartingPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.starting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)


        //кнопка "Выход"
        btn_exit.setOnClickListener{
            System.exit(-1)
        }

        //кнопка Настройки
       // btn_set.setOnClickListener {
        //    presenter.setting()
        //}

        button_auth.setOnClickListener() {
            showError ("Авторизация")


        }

        btn_1game.setOnClickListener() {
            presenter.game1("Одиночная игра")
        }

        btn_2game.setOnClickListener() {
            showError ("Игра на двоих")
        }


    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}