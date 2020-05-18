package com.example.myapplication1.presentation.gametwoplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
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
            if (nextmove>2) {
                butgame2.isVisible=false
                funnextmove()
                //textView.isVisible=true
            }

            if (nextmove==2) {
                gamer2.ships = gameView2.scanships()
                gameViewPlayTwo2.isVisible=true
                gameViewPlayTwo2.ident(gamer1.ships)
                gameView2.hiddenships(gamer2.ships)
                butgame1.isVisible=false
                butgame2.isVisible=false
                textView.text="Игрок 1"
                nextmove++
            }

            if (nextmove==1)
            {gamer1.ships=gameView2.scanships()
                nextmove=2
                butgame2.text="в бой"
                textView.text="Игрок 2"
            }
        }
    }

    private fun onClick(x: Float, y: Float): Boolean {
        gameView2.stek=1
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
        gamer.shotondeck(x,y) //палуба x,y получает state = 0
        if (gamer.chekkillship(gamer.ships[gamer.numbership(x,  y).first])) //если убит корабль, с координатами x y
        gameView2.shotaroundshipGV(gamer.ships[gamer.numbership(x, y).first].part)  //то расстреляны соседние ячейки

        textView3.text=gamer2.schet().toString()+":"+gamer1.schet().toString()
    }

    fun fall(){
        nextmove++
        gameViewPlayTwo2.scanships()
        gameView2.scanships()
        butgame2.isVisible=true
        textView.text="--"
    }

    fun funnextmove () {
        when (nextmove%2){
            1-> {textView.text="Игрок 1"
                gameViewPlayTwo2.ident(gamer1.ships)
                gameView2.hiddenships(gamer2.ships)
                for (i in 0..(gamer1shot.size-1)) {
                    if (gameView2.shotsquare(gamer1shot[i].first,gamer1shot[i].second)==1)
                    luckyshotgV (gamer2, gamer1shot[i].first,gamer1shot[i].second)
                }
                for (i in 0..(gamer2shot.size-1)) {
                    if (gameViewPlayTwo2.shotsquare(gamer2shot[i].first,gamer2shot[i].second)==1)
                    {luckyshotgVPT (gamer1, gamer2shot[i].first,gamer2shot[i].second)
                    }
                }
            }
            0-> {textView.text="Игрок 2"
                gameViewPlayTwo2.ident(gamer2.ships)
                gameView2.hiddenships(gamer1.ships)
                for (i in 0..(gamer2shot.size-1)) {
                    if (gameView2.shotsquare(gamer2shot[i].first,gamer2shot[i].second)==1)
                    luckyshotgV (gamer1, gamer2shot[i].first,gamer2shot[i].second)
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
        gamer.shotondeck(x,y) //палуба x,y получает state = 0
        if (gamer.chekkillship(gamer.ships[gamer.numbership(x,  y).first])) //если убит корабль, с координатами x y,
        gameViewPlayTwo2.shotaroundshipGVPT(gamer.ships[gamer.numbership(x,y).first].part)   //то расстреляны соседние ячейки

        return gamer.chekkillship(gamer.ships[gamer.numbership(x,  y).first])
    }



   /* override fun showGameOffline() {
        replace(GameOfflineFragment())
    }*/
}

