package com.example.myapplication1.presentation.gametwoplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.myapplication1.Base.ABaseActivity
import com.example.myapplication1.R
import com.example.myapplication1.presentation.App
import com.example.myapplication1.presentation.game.ui.Gamer
import com.example.myapplication1.presentation.game.ui.Ships
import com.example.myapplication1.presentation.gameoffline.GameOfflineFragment
import com.example.myapplication1.presentation.gameoffline.IActivityGameOffline
import kotlinx.android.synthetic.main.activity_game_two_player.*
import kotlinx.android.synthetic.main.activity_game_two_player.gameView2
import kotlinx.android.synthetic.main.gameoffline.*

class ActivityGameTwoPlayer : ABaseActivity(), IActivityGameTwoPlayer  {


    companion object {

        private const val ARG_DROP_CREDENTIALS = "ARG_DROP_CREDENTIALS"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, ActivityGameTwoPlayer::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK // or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ARG_DROP_CREDENTIALS, true)
                })
            }
        }
    }

    var gamer1 = Gamer(1, mutableListOf<Ships>())
    var gamer2 = Gamer(1, mutableListOf<Ships>())
    var nextmove :Int = 1
    var gamer1shot = mutableListOf<Pair<Int,Int>>()
    var gamer2shot = mutableListOf<Pair<Int,Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_two_player)

        //showGameOffline()

        gameView2.setOnTouchListener { _, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> onClick(event.x, event.y)
                // MotionEvent.ACTION_MOVE -> onMove (event.x, event.y)
                else -> false
            }

        }

        butgame1.setOnClickListener(){
            gameView2.scanships()
            gameView2.setsh()


        }

        butgame2.setOnClickListener {



            if (nextmove==2) {
                gamer2.ships = gameView2.scanships()


              //  gameViewPlayTwo.isVisible=true

                gameViewPlayTwo2.isVisible=true
                gameViewPlayTwo2.ident(gamer1.ships)
                gameView2.hiddenships(gamer2.ships)
                butgame1.isVisible=false
                butgame2.isVisible=false
                nextmove++

            }

            if (nextmove==1)
            {gamer1.ships=gameView2.scanships()
                nextmove=2
                butgame2.text="в бой"


            }



        }



    }

    private fun onClick(x: Float, y: Float): Boolean {

        gameView2.stek=1

        //if (gameViewPlayTwo.isVisible)

        when (nextmove%2) {
            1 -> {gamer1shot.add(gameView2.xyfloattoint(x,y))
                when (gameView2.onClick(x, y)) {
                    1 -> luckyshotgV(gamer2, gameView2.xyfloattoint(x,y).first, gameView2.xyfloattoint(x,y).second)
                    2 -> fall()
                }
                  }


            0 -> {gamer2shot.add(gameView2.xyfloattoint(x,y))
                when (gameView2.onClick(x, y)) {
                1 -> luckyshotgV(gamer1, gameView2.xyfloattoint(x,y).first, gameView2.xyfloattoint(x,y).second)
                2 -> fall()
            }}
        }

        return true
    }

    fun luckyshotgV (gamer:Gamer, x:Int,y:Int) {

        gamer.ships[numbership(gamer.ships, x,  y).first].part[numbership(gamer.ships, x,y).second].state=0
        if  (chekkilledship(gamer.ships[numbership(gamer.ships, x,  y).first]))
            gameView2.shotaroundshipGV(gamer.ships[numbership(gamer.ships, x,  y ).first].part)

        //if (chekkilledgamer(gamer2))
        // {gameViewPlayTwo.isVisible=false
        //     gameView.isVisible=false
        //}
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

    fun fall(){
        nextmove++
        gameViewPlayTwo2.scanships()
        gameView2.scanships()
        when (nextmove%2){
            1-> {
                gameViewPlayTwo2.ident(gamer1.ships)
                gameView2.hiddenships(gamer2.ships)
                for (i in 0..(gamer1shot.size-1)) {
                 if (gameView2.shotsquare(gamer1shot[i].first,gamer1shot[i].second)==1)
                 {luckyshotgV (gamer2, gamer1shot[i].first,gamer1shot[i].second)

                 }
                   }
                for (i in 0..(gamer2shot.size-1)) {
                    if (gameViewPlayTwo2.shotsquare(gamer2shot[i].first,gamer2shot[i].second)==1)
                    {luckyshotgVPT (gamer1, gamer2shot[i].first,gamer2shot[i].second)

                    }
                }
            }
           0-> {gameViewPlayTwo2.ident(gamer2.ships)
                gameView2.hiddenships(gamer1.ships)
               for (i in 0..(gamer2shot.size-1)) {
                   if (gameView2.shotsquare(gamer2shot[i].first,gamer2shot[i].second)==1)
                   {luckyshotgV (gamer1, gamer2shot[i].first,gamer2shot[i].second)

                   }
               }
               for (i in 0..(gamer1shot.size-1)) {
                   if (gameViewPlayTwo2.shotsquare(gamer1shot[i].first,gamer1shot[i].second)==1)
                   {luckyshotgVPT (gamer2, gamer1shot[i].first,gamer1shot[i].second)

                   }
               }



           }
        }

    }

    fun luckyshotgVPT (gamer:Gamer, x:Int,y:Int):Boolean {

        gamer.ships[numbership(gamer.ships, x, y).first].part[numbership(gamer.ships, x,y).second].state=0
        if  (chekkilledship(gamer.ships[numbership(gamer.ships, x,y ).first])) //если убит корабль, то расстреляны соседние ячейки
            gameViewPlayTwo2.shotaroundshipGVPT(gamer.ships[numbership(gamer.ships, x,y).first].part)
        return chekkilledship(gamer.ships[numbership(gamer.ships, x,y ).first])
    }

   /* override fun showGameOffline() {
        replace(GameOfflineFragment())
    }*/
}

