package com.frezzcoding.savingsguru.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.core.content.ContextCompat
import androidx.core.graphics.toRectF
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.highestNumber

class LineGraph(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var linePaint: Paint
    private var mBlackPaint: Paint
    private var plotPaint : Paint
    private var gradientPaint : Paint
    private var mPath: Path
    private var xAxisTotal = 0f
    private var yAxisTotal = 0f
    private lateinit var dataPoints : List<Int> //make data object to hold date & value
    private var highestValue : Int = 0
    private var amountOfValues : Int = 0
    private var zeroY = 0F
    private var setSize = 3

    init {
        linePaint = Paint()
        mPath = Path()
        mBlackPaint = Paint()
        plotPaint = Paint()
        gradientPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        xAxisTotal = (width / amountOfValues).toFloat()
        yAxisTotal = (height / (highestValue + (if(highestValue < 1) 1 else (highestValue / 10)))).toFloat()
        zeroY = (highestValue * (height / highestValue - 0) + paddingTop).toFloat()
        initializePaint()

        drawGraphPlotAndLines(canvas)
        drawGraphFilling(canvas)
        drawAxis(canvas)
        drawButtons(canvas)
    }

    private fun drawButtons(canvas: Canvas?){
        var textPaint = Paint()
        textPaint.color = Color.GRAY
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textSize = 45F
        //var rectPaint = Paint()
        //rectPaint.color = Color.WHITE
        //rectPaint.style = Paint.Style.FILL

        var week = 0
        for(i in 0..dataPoints.size step setSize){
            week ++
            var rect = Rect()
            //rect.set((xAxisTotal.toInt() * ((i*1.5) + 1.5)).toInt(), height - 90, xAxisTotal.toInt() * 3, zeroY.toInt())
            //rectPaint.color = Color.DKGRAY
            //rectPaint.style = Paint.Style.STROKE
            //canvas?.drawRoundRect(rect.toRectF(), 550f,350f, rectPaint)
            canvas?.drawText(
                    "wk $week",
                    (xAxisTotal.toInt() * ((i) + 1.5)).toFloat() + (rect.width() / 3 ) ,
                    zeroY ,
                    textPaint)

        }
    }

    private fun initializePaint(){
        mBlackPaint.color = Color.BLACK
        mBlackPaint.style = Paint.Style.STROKE
        mBlackPaint.strokeWidth = 10F

        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = 10F
        linePaint.color = ContextCompat.getColor(context, R.color.green)

        //prepare gradient
        val colors = intArrayOf(R.color.purple_500, R.color.green)
        val gradient = LinearGradient(
                xAxisTotal, paddingTop.toFloat(), 0f, zeroY, colors, null, Shader.TileMode.CLAMP
        )
        gradientPaint.style = Paint.Style.FILL
        gradientPaint.shader = gradient

        plotPaint.color = ContextCompat.getColor(context, R.color.blue)
    }

    private fun drawGraphFilling(canvas: Canvas?){
        mPath.reset()
        mPath.moveTo(paddingLeft.toFloat() + xAxisTotal, height - yAxisTotal - 100f)
        var iteration = 2
        dataPoints.forEach {
            mPath.lineTo(xAxisTotal*iteration, (height - yAxisTotal) - (it.toFloat() * yAxisTotal) - 100f)
            iteration ++
        }
        mPath.lineTo(xAxisTotal*(dataPoints.size + 1), height - yAxisTotal - 100f)
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
        var dottedPaint = Paint()
        dottedPaint.style = Paint.Style.STROKE
        dottedPaint.strokeWidth = 3F
        dottedPaint.pathEffect = DashPathEffect(floatArrayOf(22F, 22F), 0f)

        for(i in 0..dataPoints.size step setSize){
            mPath.reset()
            mPath.moveTo(xAxisTotal * (1+i), paddingTop.toFloat())
            mPath.lineTo(xAxisTotal * (1+i), height - yAxisTotal - 100F)
            canvas?.drawPath(mPath, dottedPaint)
        }

        //canvas?.drawLine(xAxisTotal, yAxisTotal, xAxisTotal, (height - 10).toFloat(), mBlackPaint)
        //canvas?.drawLine(10F, height - yAxisTotal, width - xAxisTotal, height - yAxisTotal, mBlackPaint)
    }

    private fun drawGraphPlotAndLines(canvas: Canvas?){
        var originX = xAxisTotal
        var originY = height - yAxisTotal
        mPath.moveTo(originX, originY - 100f)

        dataPoints.forEach{
            mPath.lineTo(originX + xAxisTotal, originY - (it * yAxisTotal) - 100f)
            canvas?.drawCircle(originX + xAxisTotal, originY - (it * yAxisTotal) - 100f, 10F, plotPaint)
            originX += xAxisTotal
        }
        canvas?.drawPath(mPath, linePaint)
    }
}