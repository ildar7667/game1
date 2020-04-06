package com.example.myapplication1.presentation.gameoffline

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.Base.ABaseFragment
import com.example.myapplication1.R
import com.example.myapplication1.domain.di.components.DaggerAppComponent
import com.example.myapplication1.presentation.game.GameView
import com.example.myapplication1.presentation.game.ui.PlayingFieldUI
import com.example.myapplication1.presentation.main.MainActivity
import kotlinx.android.synthetic.main.gameoffline.*
import javax.inject.Inject


class GameOfflineFragment: ABaseFragment(), IGameOfflineView {


    @Inject
    @InjectPresenter
    lateinit var presenter: GameOfflinePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        DaggerAppComponent.create().injectgame(this)
    }

    override fun getViewId() = R.layout.gameoffline

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        butgameoffline.setOnClickListener {

            //val gv = GameView(Context)

            presenter.gameoffline("Одиночная игра")
            //MainActivity.show()
            // GameView.onAttachedToWindow()
            //gv.render()
           // gameView.render()
            //GameView(gameView.context).isAttachedToWindow()
            //GameView(gameView.context)

                   }


      /*
        btnlogof.setOnClickListener{
            MainActivity.show()
        }
        */
    }

    override fun onSuccess() {
        toast("SUCCESS")
    }

}