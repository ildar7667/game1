package com.example.myapplication1.presentation.gameplay

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.myapplication1.presentation.game.GameView
import com.example.myapplication1.presentation.game.model.GameState
import com.example.myapplication1.presentation.game.ui.PlayingFieldUI
import com.example.myapplication1.presentation.game.ui.Ships
import com.example.myapplication1.presentation.game.ui.TakeUI
import kotlinx.android.synthetic.main.gameoffline.*
import kotlinx.android.synthetic.main.gameoffline.view.*
import java.util.concurrent.TimeUnit
import kotlin.math.min

class GameViewPlayTwo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) { render() }
    override fun surfaceDestroyed(p0: SurfaceHolder?) { }
    override fun surfaceCreated(p0: SurfaceHolder?) { render() }

    private val playingField = PlayingFieldUI()

    var onSelectListener: ((TakeUI) -> Unit)? = null
    var stek:Int = 0


    init {

        holder.addCallback(this)

        setOnTouchListener { _, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> onClick(event.x, event.y)
                // MotionEvent.ACTION_MOVE -> onMove (event.x, event.y)
                else -> false
            }
        }

       // k=gameView.scanships()
       // playingField.identships(k)
        //post({ render() })

    }



    fun ident(k:MutableList<Ships>){
        playingField.identships(k)
        post({ render() })
    }




    fun render() {

        var canvas: Canvas? = null

        try {

            canvas = holder.lockCanvas()
            if (canvas != null)
                render(canvas)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            canvas?.let { holder.unlockCanvasAndPost(it) }
        }
    }



    private fun render(canvas: Canvas) {

        val hw = width * 0.5f
        val hh = height * 0.5f
        val cx = x + hw
        val cy = y + hh
        val min = min(width, height)
        val mh = min * 0.5f

        playingField.x = (cx - mh).toInt()
        playingField.y = (cy - mh).toInt()
        playingField.width = min
        playingField.height = min

        playingField.render(canvas)
    }

    private fun onMove(x: Float, y: Float) : Boolean {

        playingField.onMoveSquare(x, y)
        post({ render() })
        return true
    }


    fun shotsquare (x:Int, y:Int):Int {
        var ret: Int
        ret=playingField.shotxy(x,y)
        post({ render() })
        return ret

    }

    private fun onClick(x: Float, y: Float): Boolean {
        // setsh()

        if (stek==1){
        playingField.onClickSquare(x, y)
        post({ render() })
        stek=0
           // gameView.stek=1
        }

        val listener = onSelectListener ?: return false

        playingField.onClick(x, y)?.let {
            if (it.state == TakeUI.STATE_UNDEFINED)
                listener(it)

            return true
        }

        return false
    }


}