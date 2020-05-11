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
import com.example.myapplication1.presentation.game.ui.PartShips
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

    var k1  = mutableListOf<Ships>()
    var k2  = mutableListOf<Ships>()
    var gamer1 = Gamer(1, k1)
    var gamer2 = Gamer(1, k2)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        gameView.setOnTouchListener { _, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> onClick(event.x, event.y)
                // MotionEvent.ACTION_MOVE -> onMove (event.x, event.y)
                else -> false
            }

        }

        butgameoffline.setOnClickListener {
            gameView.scanships()
            gameView.setsh()

                  }


        butscan.setOnClickListener{

            k1=gameView.scanships()

            gameViewPlayTwo.isVisible=true
            gameViewPlayTwo.ident(k1)

            gameView.setsh()

            k2=gameView.scanships()
            gameView.hiddenships(k2)

            butgameoffline.isVisible=false
            butscan.isVisible=false

        }
    }



    private fun onClick(x: Float, y: Float): Boolean {

        gameView.stek=1
        when (gameView.onClick(x,y))
        {
            1 -> luckyshot (x,y)
            2 -> fall()
                 }
        return true
    }

    fun luckyshot (x:Float,y:Float) {

        k2[numbership(k2, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first].part[numbership(k2, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).second].state=0
        if  (chekkilledship(k2[numbership(k2, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first]))
            gameView.shotaroundshipGV(k2[numbership(k2, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first].part)
    }

    fun fall(){
        var result:Int=0
        do {
            val randome = Random(System.nanoTime())
            var x: Int = randome.nextInt(10)
            var y: Int = randome.nextInt(10)

            // TimeUnit.SECONDS.sleep(1)
            // Thread.sleep(2000)
            result=gameViewPlayTwo.shotsquare(x, y)
        } while (result==1 || result==3)

    }

    fun numbership (listship: MutableList<Ships>, x:Int, y:Int) :Pair <Int, Int> { //возвращает номер корабля, по которому попал выстрел от 0 по 9. И номер палубы
        var shotship:Int=0
        var shotpart:Int=0

            for (i in (0..listship.size-1))
            for (j in 0..(listship[i].part.size-1))
                if ((listship[i].part[j].x==x) && (listship[i].part[j].y==y))
                {shotship=i
                    shotpart=j
                 //listship[i].part[j].state=0
                }
      return Pair(shotship, shotpart)
    }

    fun chekkilledship (ship: Ships):Boolean{ //проверяет убит ли корабль, true- убит
        var  n: Int=0
        for (i in 0..(ship.part.size-1))
            if  (ship.part[i].state==0)
        n++

        if (ship.part.size==n)
        {
            ship.state=0
            return true
        }

      return false

      }

    override fun onSuccess() {
        toast("SUCCESS")
    }

}