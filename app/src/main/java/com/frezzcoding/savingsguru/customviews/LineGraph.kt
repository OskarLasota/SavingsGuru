package com.frezzcoding.savingsguru.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.highestNumber
import com.frezzcoding.savingsguru.common.lowestNumber

class LineGraph(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var linePaint: Paint
    private var mBlackPaint: Paint
    private var plotPaint : Paint
    private var gradientPaint : Paint
    private var mPath: Path
    private var dataPoints = listOf<Int>() //make data object to hold date & value
    private var highestValue : Int = 0
    private var lowestValue : Int = 0
    private var amountOfValues : Int = 0
    private var zeroY = 0F

    private var widthPerItem : Float = 0F
    private var heightPerValue : Float = 0F
    private var paddingForButtons : Float = 0f
    private var graduationPadding : Float = 100f
    private var amountOfGraduations = 6
    private var highestY = 0f


    //todo compatibility with negative numbers
    //todo make changes so that wrap content doesn't occupy entire screen
    init {
        linePaint = Paint()
        mPath = Path()
        mBlackPaint = Paint()
        plotPaint = Paint()
        gradientPaint = Paint()
    }

    fun setDataPoints(data: List<Int>) {
        dataPoints = data
        dataPoints.highestNumber()?.let { highestNumber ->
            highestValue = highestNumber
        }
        dataPoints.lowestNumber()?.let { lowestNumber ->
            lowestValue = lowestNumber
        }
        if(highestValue - lowestValue < 0){
            //handle divide by zero error
            handleError()
        }
        amountOfValues = dataPoints.size
        invalidate()
    }

    private fun handleError(){

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //draw different type of graph for smaller than 1 values, draw only line graph
        if(lowestValue < 0){
            //notify that graph does not support negative numbers
        }else if (dataPoints.size < 2) {
            //handle empty graph
        }else {
            widthPerItem = ((width - paddingLeft - paddingRight - graduationPadding) / amountOfValues)
            var totalHeight = (height - paddingTop - paddingBottom).toFloat()
            heightPerValue = totalHeight/highestValue
            zeroY = (height - paddingBottom).toFloat()
            initializePaint()
            drawGraphPlotAndLines(canvas)
            drawGraphFilling(canvas)
            drawGraduations(canvas)
        }
    }

    private fun drawGraduations(canvas: Canvas?){
        val x = (widthPerItem * amountOfValues.toFloat()) + paddingRight
        var y : Float
        val textPaint = Paint()
        textPaint.color = Color.GRAY
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textSize = 40F
        for(i in 0..amountOfGraduations){
            y = zeroY - ((((zeroY - highestY)/ amountOfGraduations) * i) )
            if(i != 0){
                canvas?.drawText(((highestValue.toDouble() / amountOfGraduations) * i).toInt().toString(), x, y, textPaint)
                drawAxis(canvas, y)
            }else{
                canvas?.drawText(0.toString(), x, y, textPaint)
            }
        }
        canvas?.drawText(highestValue.toString(), x, zeroY - ((zeroY / amountOfGraduations) * 7), textPaint)
    }

    private fun drawAxis(canvas: Canvas?, yAxis : Float) {
        val dottedPaint = Paint()
        dottedPaint.style = Paint.Style.STROKE
        dottedPaint.strokeWidth = 3F
        dottedPaint.pathEffect = DashPathEffect(floatArrayOf(22F, 22F), 0f)

        for(i in 1..(width*0.9).toInt() step width / 10){
            mPath.reset()
            mPath.moveTo(i.toFloat(), yAxis)
            mPath.lineTo(i+20f, yAxis)
            canvas?.drawPath(mPath, dottedPaint)
        }

        //canvas?.drawLine(xAxisTotal, yAxisTotal, xAxisTotal, (height - 10).toFloat(), mBlackPaint)
        //canvas?.drawLine(10F, height - yAxisTotal, width - xAxisTotal, height - yAxisTotal, mBlackPaint)
    }


    /*
    private fun drawButtons(canvas: Canvas?){
        val textPaint = Paint()
        textPaint.color = Color.GRAY
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textSize = 45F
        //var rectPaint = Paint()
        //rectPaint.color = Color.WHITE
        //rectPaint.style = Paint.Style.FILL

        var week = 0
        for(i in 0..dataPoints.size step setSize){
            week ++
            val rect = Rect()
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
     */

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
                widthPerItem, paddingTop.toFloat(), 0f, zeroY, colors, null, Shader.TileMode.CLAMP
        )
        gradientPaint.style = Paint.Style.FILL
        gradientPaint.shader = gradient

        plotPaint.color = ContextCompat.getColor(context, R.color.blue)
    }

    private fun drawGraphFilling(canvas: Canvas?){
        mPath.reset()
        mPath.moveTo(paddingLeft.toFloat() + 0f, zeroY - paddingForButtons)
        var iteration = 1
        dataPoints.forEach {
            mPath.lineTo(widthPerItem * iteration + paddingLeft, zeroY - (it.toFloat() * heightPerValue) - paddingForButtons)
            iteration ++
        }
        mPath.lineTo((widthPerItem * amountOfValues + paddingLeft), zeroY - paddingForButtons)
        canvas?.drawPath(mPath, gradientPaint)
    }



    /*
    private fun drawAxis(canvas: Canvas?) {
        val dottedPaint = Paint()
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
     */

    private fun drawGraphPlotAndLines(canvas: Canvas?){
        mPath.reset()
        var originX = widthPerItem + paddingLeft
        val originY = zeroY - paddingForButtons
        mPath.moveTo(0f + paddingLeft, originY)

        dataPoints.forEach{
            if(it == highestValue){
                highestY = originY - (it * heightPerValue)
            }
            mPath.lineTo(originX, originY - (it * heightPerValue))
            canvas?.drawCircle(originX, originY - (it * heightPerValue), 10F, plotPaint)
            originX += widthPerItem
        }
        canvas?.drawPath(mPath, linePaint)
    }
}