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

   // var k1  = mutableListOf<Ships>()
    //var k2  = mutableListOf<Ships>()
    var gamer1 = Gamer(1, mutableListOf<Ships>())
    var gamer2 = Gamer(1, mutableListOf<Ships>())

    var a:Int=20
    var b:Int=20
    var vec:Int=0
    var len:Int=0

    val randome = Random(System.nanoTime())

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

           // k1=gameView.scanships()

            gamer1.ships=gameView.scanships()

            gameViewPlayTwo.isVisible=true
            gameViewPlayTwo.ident(gamer1.ships)

            gameView.setsh()

            gamer2.ships=gameView.scanships()
            gameView.hiddenships(gamer2.ships)

            butgameoffline.isVisible=false
            butscan.isVisible=false

        }
    }



    private fun onClick(x: Float, y: Float): Boolean {

        gameView.stek=1
        when (gameView.onClick(x,y))
        {
            1 -> luckyshotgV (x,y)
            2 -> fall()
                 }
        return true
    }

    fun luckyshotgV (x:Float,y:Float) {

        gamer2.ships[numbership(gamer2.ships, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first].part[numbership(gamer2.ships, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).second].state=0
        if  (chekkilledship(gamer2.ships[numbership(gamer2.ships, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first]))
            gameView.shotaroundshipGV(gamer2.ships[numbership(gamer2.ships, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first].part)

        //if (chekkilledgamer(gamer2))
       // {gameViewPlayTwo.isVisible=false
       //     gameView.isVisible=false
        //}
    }

    fun luckyshotgVPT (x:Int,y:Int):Boolean {

        gamer1.ships[numbership(gamer1.ships, x, y).first].part[numbership(gamer1.ships, x,y).second].state=0
        if  (chekkilledship(gamer1.ships[numbership(gamer1.ships, x,y ).first])) //если убит корабль, то расстреляны соседние ячейки
            gameViewPlayTwo.shotaroundshipGVPT(gamer1.ships[numbership(gamer1.ships, x,y).first].part)
        return chekkilledship(gamer1.ships[numbership(gamer1.ships, x,y ).first])
    }

    fun fall(){
        var result:Int=0
        var x: Int = 0
        var y: Int = 0


        do {   x = randome.nextInt(10)
               y = randome.nextInt(10)

            // TimeUnit.SECONDS.sleep(1)
            // Thread.sleep(2000)
                result=gameViewPlayTwo.shotsquare(x, y)

            if (result==1)
               if (luckyshotgVPT(x,y)!=true) //если корабль не убит
               {
                   a = x
                   b = y
                   len = 1
                   if (a - 1 != -1) {
                       result = gameViewPlayTwo.shotsquare(a - 1, b)
                       if (result == 1) {
                           vec = 4 //влево
                           len = 2

                         }

                         }
                         else if (a+1!=10 || result==2) { //попал повторно в пусто поле
                       result = gameViewPlayTwo.shotsquare(a + 1, b)
                       if (result == 1) {
                           vec = 2 //вправо
                           len = 2
                               }
                            }
                                 else if (b+1!=10 || result==2) { //попал повторно в пусто поле
                       result = gameViewPlayTwo.shotsquare(a , b+1)
                       if (result == 1) {
                           vec = 3 //вниз
                           len = 2
                       }
                   }
                              else if (b-1!=-1 || result==2) { //попал повторно в пусто поле
                               result = gameViewPlayTwo.shotsquare(a , b-1)
                              if (result == 1) {
                               vec = 1 //вверх
                               len = 2
                                }
                   }

               }
            /*
                else {
                      a=x
                      b=y
                     if (vec==0)
                           {    if (a-1!=-1)
                                 {  result=gameViewPlayTwo.shotsquare(a-1, b)

                                 }

                           }

                     }

            }
              */

            } while (result==4 || result==3 || result==1)



       /* if (result==1) //попал в корабль, добавить проверку если корабль не убит сразу
        {   a=x
            b=y
            len=1

            if (vec==0)
            {when (randome.nextInt(4))
                 { 0-> {}

                 }

            }

        }*/
      //  else
        /*{

            if (vec==0)
                when (randome.nextInt(3))
                {


                }

        }*/


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


    fun chekkilledgamer (gamer: Gamer):Boolean {
        var n=0

        for (i in 0..gamer.ships.size-1)
            if (gamer.ships[i].state==1) {
                n = 1
                return false
            }
        return true
    }

    override fun onSuccess() {
        toast("SUCCESS")
    }

}