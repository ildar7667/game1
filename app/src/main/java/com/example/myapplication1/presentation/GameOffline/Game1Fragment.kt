package com.example.myapplication1.presentation.GameOffline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.R
import com.example.myapplication1.presentation.starting.IGame1View
import kotlinx.android.synthetic.main.gameplay.*


class Game1Fragment : MvpAppCompatFragment(),
    IGame1View {

    @InjectPresenter
    lateinit var presenter: Game1Presenter

    @ProvidePresenter
    fun providePresenter() =
        Game1Presenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gameplay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener(){
            presenter.game1("Одиночная игра")
        }


    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}