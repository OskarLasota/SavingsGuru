package com.frezzcoding.savingsguru.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.highestNumber

class LineGraph(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var mPaint: Paint
    private var mBlackPaint: Paint
    private var plotPaint : Paint
    private var gradientPaint : Paint
    private var mPath: Path
    private var mXUnit = 0f
    private var mYUnit = 0f
    private lateinit var dataPoints : List<Int> //make data object to hold date & value
    private var highestValue : Int = 0
    private var amountOfValues : Int = 0
    private var zeroY = 0F

    init {
        mPaint = Paint()
        mPath = Path()
        mBlackPaint = Paint()
        plotPaint = Paint()
        gradientPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mBlackPaint.color = Color.BLACK
        mBlackPaint.style = Paint.Style.STROKE
        mBlackPaint.strokeWidth = 10F
        mXUnit = (width / amountOfValues).toFloat() // 10 plots for x axis and 2 left for padding
        mYUnit = (height / highestValue).toFloat()
        zeroY = (highestValue * (height / highestValue - 0) + paddingTop).toFloat()
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10F
        mPaint.color = ContextCompat.getColor(context, R.color.green)

        //prepare gradient
        val colors = intArrayOf(R.color.purple_500, R.color.green)
        val gradient = LinearGradient(
                mXUnit, paddingTop.toFloat(), 51f, zeroY, colors, null, Shader.TileMode.CLAMP
        )
        gradientPaint.style = Paint.Style.FILL
        gradientPaint.shader = gradient

        plotPaint.color = ContextCompat.getColor(context, R.color.blue)
        drawAxis(canvas)
        drawGraphPlotAndLines(canvas)
        drawGraphFilling(canvas)
    }
    private fun drawGraphFilling(canvas: Canvas?){
        mPath.reset()
        mPath.moveTo(paddingLeft.toFloat() + mXUnit, height - mYUnit)
        var iteration = 2
        dataPoints.forEach {
            mPath.lineTo(mXUnit*iteration, (height - mYUnit) - (it.toFloat() * mYUnit))
            iteration ++
        }
        mPath.lineTo(mXUnit*(dataPoints.size + 1), height - mYUnit)
        canvas?.drawPath(mPath, gradientPaint)
    }

    fun setDataPoints(data : List<Int>) {
        dataPoints = data
        data.highestNumber()?.let {highestNumber ->
            highestValue = highestNumber + (highestNumber / 10) // increase proportionally
        }
        amountOfValues = data.size + 2
    }

    private fun drawAxis(canvas: Canvas?) {
        canvas?.drawLine(mXUnit, mYUnit, mXUnit, (height - 10).toFloat(), mBlackPaint)
        canvas?.drawLine(10F, height - mYUnit, width - mXUnit, height - mYUnit, mBlackPaint)
    }

    private fun drawGraphPlotAndLines(canvas: Canvas?){
        var originX = mXUnit
        var originY = height - mYUnit
        mPath.moveTo(originX, originY)

        dataPoints.forEach{
            mPath.lineTo(originX + mXUnit, originY - (it * mYUnit))
            canvas?.drawCircle(originX + mXUnit, originY - (it * mYUnit), 10F, plotPaint)
            originX += mXUnit
        }
        canvas?.drawPath(mPath, mPaint)
    }
}