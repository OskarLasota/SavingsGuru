package com.frezzcoding.savingsguru.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class LineGraph(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var mPaint: Paint
    private var mPath: Path
    private var mXUnit = 0f
    private var mYUnit = 0f
    private var mBlackPaint: Paint
    private lateinit var dataPoints : List<Int> //make data object to hold date & value

    init {
        mPaint = Paint()
        mPath = Path()
        mBlackPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mBlackPaint.color = Color.BLACK
        mBlackPaint.style = Paint.Style.STROKE
        mBlackPaint.strokeWidth = 10F
        mXUnit = (width / 12).toFloat() // 10 plots for x axis and 2 left for padding
        mYUnit = (height / 12).toFloat()

        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10F
        mPaint.color = Color.GREEN
        drawAxis(canvas)
        drawGraphPlotLines(canvas)
        drawGraphPaper(canvas)
    }

    fun setDataPoints(data : List<Int>){
        dataPoints = data
    }

    private fun drawAxis(canvas: Canvas?) {
        canvas?.drawLine(mXUnit, mYUnit, mXUnit, (height - 10).toFloat(), mBlackPaint)
        canvas?.drawLine(10F, height - mYUnit, width - mXUnit, height - mYUnit, mBlackPaint)
    }

    private fun drawGraphPlotLines(canvas: Canvas?){
        var originX = mXUnit
        var originY = height - mYUnit
        mPath.moveTo(originX, originY)

        dataPoints.forEach{
            mPath.lineTo(originX + mXUnit, originY - (it * mYUnit))
            canvas?.drawCircle(originX + mXUnit, originY - (it * mYUnit), 5F, mPaint)
            originX += mXUnit
        }
        canvas?.drawPath(mPath, mPaint)
    }

    private fun drawGraphPaper(canvas: Canvas?){
        var cx = mXUnit
        var cy = height - mYUnit

        mBlackPaint.strokeWidth = 1F

        for (i in 1..11) {
            canvas?.drawLine(cx, mYUnit, cx, cy, mBlackPaint)
            cx += mXUnit
        }
        cx = mXUnit
        for(i in 1..11){
            canvas?.drawLine(cx, cy, width - mXUnit, cy, mBlackPaint)
            cy -= mYUnit
        }
    }

}