package com.example.myapplication1.presentation.gameoffline

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myapplication1.Base.ABaseFragment
import com.example.myapplication1.R
import com.example.myapplication1.domain.di.components.DaggerAppComponent
import com.example.myapplication1.presentation.game.ui.Gamer
import com.example.myapplication1.presentation.game.ui.Ships
import kotlinx.android.synthetic.main.gameoffline.*
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
        var xx:Int=gameView.xyfloattoint(x,y).first
        var yy:Int=gameView.xyfloattoint(x,y).second

        gamer2.shotondeck(xx,yy)
        //gamer2.ships[numbership(gamer2.ships, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).first].part[numbership(gamer2.ships, gameView.xyfloattoint(x,y).first,  gameView.xyfloattoint(x,y).second ).second].state=0
        if (gamer2.chekkillship(gamer2.ships[gamer2.numbership(xx,yy).first]))
        gameView.shotaroundshipGV(gamer2.ships[gamer2.numbership(xx,yy).first].part)

     }

    fun luckyshotgVPT (x:Int,y:Int):Boolean {

        gamer1.shotondeck(x,y)  //присваивает палубе с координатами x y state=0

        if  (gamer1.chekkillship(gamer1.ships[gamer1.numbership(x,y).first])) //если убит корабль, то расстреляны соседние ячейки
            gameViewPlayTwo.shotaroundshipGVPT(gamer1.ships[gamer1.numbership(x,y).first].part)
        return gamer1.chekkillship(gamer1.ships[gamer1.numbership(x,y).first])
    }

    fun fall(){
        var result:Int=0
        var x: Int = 0
        var y: Int = 0

        if (gameViewPlayTwo.isVisible)
        do {   x = randome.nextInt(10)
               y = randome.nextInt(10)

            // TimeUnit.SECONDS.sleep(1)
            // Thread.sleep(2000)
                result=gameViewPlayTwo.shotsquare(x, y)

            if (result==1)
               if (luckyshotgVPT(x,y)!=true) //если корабль не убит
               {/*
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
*/
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