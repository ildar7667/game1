package com.example.myapplication1.presentation.gameoffline

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.Base.ABaseFragment
import com.example.myapplication1.R
import com.example.myapplication1.domain.di.components.DaggerAppComponent
import com.example.myapplication1.presentation.game.GameView
import com.example.myapplication1.presentation.game.ui.Gamer
import com.example.myapplication1.presentation.game.ui.PlayingFieldUI
import com.example.myapplication1.presentation.game.ui.Ships
import com.example.myapplication1.presentation.gameplay.GamePlay
import com.example.myapplication1.presentation.gameplay.GameViewPlayTwo
import com.example.myapplication1.presentation.gameplay.StateGame
import com.example.myapplication1.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_game_play.*
import kotlinx.android.synthetic.main.gameoffline.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random


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

        var k1 : List<Ships> = mutableListOf<Ships>()
        var k2 : List<Ships> = mutableListOf<Ships>()
        var gamer1 = Gamer(1, k1)
        var gamer2 = Gamer(1, k2)


        gameView.setOnTouchListener { _, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> onClick(event.x, event.y)
                // MotionEvent.ACTION_MOVE -> onMove (event.x, event.y)
                else -> false
            }

        }

        butgameoffline.setOnClickListener {
            gameView.setsh()
            //val gv = GameView(Context)

            //presenter.gameoffline("Одиночная игра")
            //MainActivity.show()
            // GameView.onAttachedToWindow()
            //gv.render()
           // gameView.render()
            //GameView(gameView.context).isAttachedToWindow()
           // GameView(gameView.context)
            //GameView(gameView.context).setsh()

            //val textss: TextView = findViewById(R.id.TextViewGV) as TextView

          //  TextViewGV.text="shipsfour.size"
//val shipsf : Ships = setshipsfour(5)

            // text.setText("size")

                  }

        /*butgameofflinenext.setOnClickListener{

            GamePlay.show()
        }*/

        butscan.setOnClickListener{

            k1=gameView.scanships()

           // GamePlay(k)
            //gameViewPlayTwo.k=k
            //stat=StateGame(k)
           // var Stg=StateGame()
           // Stg.k=k
            //Stg.start()
            gameViewPlayTwo.isVisible=true
            gameViewPlayTwo.ident(k1)

            gameView.setsh()
            k2=gameView.scanships()
            gameView.hiddenships(k2)


            butgameoffline.isVisible=false
            butscan.isVisible=false
            //butscan.isVisible=false
            //gameViewPlayTwo.width="200dp"
            //gameViewPlayTwo.isActivated=false
            //gamer1= Gamer(1,k1)
          //  gameproc()


        }






      /*
        btnlogof.setOnClickListener{
            MainActivity.show()
        }
        */
    }


    fun gameproc(){

       // gameView.stek=1
       // gameViewPlayTwo.stek=1


        //gamer1.ships[0].part

    }

    private fun onClick(x: Float, y: Float): Boolean {
       //var shotship:Boolean=true
        gameView.stek=1

        if (gameView.onClick(x,y)==false) {

            do {
                val randome = Random(System.nanoTime())
                var x: Int = randome.nextInt(10)
                var y: Int = randome.nextInt(10)

               // TimeUnit.SECONDS.sleep(1)
               // Thread.sleep(2000)
            } while (gameViewPlayTwo.shotsquare(x, y)==true)
            }

        return true
    }

    override fun onSuccess() {
        toast("SUCCESS")
    }

}