package com.example.myapplication1.presentation.game
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.myapplication1.presentation.game.model.GameState
import com.example.myapplication1.presentation.game.ui.PartShips
import com.example.myapplication1.presentation.game.ui.PlayingFieldUI
import com.example.myapplication1.presentation.game.ui.Ships
import com.example.myapplication1.presentation.game.ui.TakeUI
import kotlin.math.min

class GameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) { render() }
    override fun surfaceDestroyed(p0: SurfaceHolder?) { }
    override fun surfaceCreated(p0: SurfaceHolder?) { render() }

     val playingField = PlayingFieldUI()

    var onSelectListener: ((TakeUI) -> Unit)? = null
    var stek:Int = 1

    init {

        holder.addCallback(this)

       /* setOnTouchListener { _, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> onClick(event.x, event.y)
               // MotionEvent.ACTION_MOVE -> onMove (event.x, event.y)
                else -> false
            }
        }*/
    }


    fun hiddenships(k:MutableList<Ships>){
        playingField.identhiddenships(k)
        post({ render() })
    }

    fun setsh(){
        playingField.setshipsx(1,4)
        playingField.setshipsx(2,3)
        playingField.setshipsx(3,2)
        playingField.setshipsx(4,1)


        post({ render() })

            }


    fun scanships(): MutableList<Ships>{
        var listshig : MutableList<Ships>
        var k:Int
        listshig=playingField.scanshipsx()

        post({ render() })
        return listshig
    }

   fun shotaroundshipGV (k: MutableList<PartShips>) {
    playingField.shotaroundship(k)

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

    fun setGameState(state: GameState) {

        playingField.setGameState(state)
        render()
    }

    private fun render(canvas: Canvas) {

        val hw = width * 0.5f
        val hh = height * 0.5f
        val cx = x + hw
        val cy = y + hh
        val min = min(width, height)
        val mh = min * 0.5f

       /* playingField.x = (cx - mh).toInt()
        playingField.y = (cy - mh).toInt()
        playingField.width = min
        playingField.height = min*/
        playingField.x = x.toInt()
        playingField.y = y.toInt()
        playingField.width = width
        playingField.height = height

        playingField.render(canvas)
    }

    private fun onMove(x: Float, y: Float) : Boolean {
        playingField.onMoveSquare(x, y)
        post({ render() })
        return true
    }

    fun onClick(x: Float, y: Float): Int {
        var ret: Int = 0
        if (stek==1) {
            ret = playingField.onClickSquare(x, y)
            post({ render() })
            //gameViewPlayTwo.isVisible=false
            //gameViewPlayTwo.stek=1
            stek=0
        }


        return ret
    }

    fun shotsquare (x:Int, y:Int):Int {
        var ret: Int=0
        ret=playingField.shotxy(x,y)
        post({ render() })
        return ret

    }

    fun xyfloattoint (x:Float,y: Float): Pair <Int,Int>
    {

       return   playingField.xyfloattointPF (x,y)
    }


}