package com.example.myapplication1.presentation.gameplay

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.myapplication1.presentation.game.model.GameState
import com.example.myapplication1.presentation.game.ui.PlayingFieldUI
import com.example.myapplication1.presentation.game.ui.TakeUI
import kotlin.math.min

class GameViewPlayTwo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) { render() }
    override fun surfaceDestroyed(p0: SurfaceHolder?) { }
    override fun surfaceCreated(p0: SurfaceHolder?) { render() }

    private val playingField = PlayingFieldUI()

    var onSelectListener: ((TakeUI) -> Unit)? = null

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

    private fun onClick(x: Float, y: Float): Boolean {
        // setsh()

        playingField.onClickSquare(x, y)
        post({ render() })

        val listener = onSelectListener ?: return false

        playingField.onClick(x, y)?.let {
            if (it.state == TakeUI.STATE_UNDEFINED)
                listener(it)

            return true
        }

        return false
    }


}