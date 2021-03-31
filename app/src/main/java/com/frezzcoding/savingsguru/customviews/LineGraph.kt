package com.frezzcoding.savingsguru.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
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
        yAxisTotal = (height / highestValue).toFloat()
        zeroY = (highestValue * (height / highestValue - 0) + paddingTop).toFloat()

        initializePaint()

        drawAxis(canvas)
        drawGraphPlotAndLines(canvas)
        drawGraphFilling(canvas)
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
        mPath.moveTo(paddingLeft.toFloat() + xAxisTotal, height - yAxisTotal)
        var iteration = 2
        dataPoints.forEach {
            mPath.lineTo(xAxisTotal*iteration, (height - yAxisTotal) - (it.toFloat() * yAxisTotal))
            iteration ++
        }
        mPath.lineTo(xAxisTotal*(dataPoints.size + 1), height - yAxisTotal)
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
        canvas?.drawLine(xAxisTotal, yAxisTotal, xAxisTotal, (height - 10).toFloat(), mBlackPaint)
        canvas?.drawLine(10F, height - yAxisTotal, width - xAxisTotal, height - yAxisTotal, mBlackPaint)
    }

    private fun drawGraphPlotAndLines(canvas: Canvas?){
        var originX = xAxisTotal
        var originY = height - yAxisTotal
        mPath.moveTo(originX, originY)

        dataPoints.forEach{
            mPath.lineTo(originX + xAxisTotal, originY - (it * yAxisTotal))
            canvas?.drawCircle(originX + xAxisTotal, originY - (it * yAxisTotal), 10F, plotPaint)
            originX += xAxisTotal
        }
        canvas?.drawPath(mPath, linePaint)
    }
}