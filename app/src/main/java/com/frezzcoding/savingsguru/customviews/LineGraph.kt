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
import com.frezzcoding.savingsguru.common.lowestNumber

class LineGraph(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var linePaint: Paint
    private var mBlackPaint: Paint
    private var plotPaint : Paint
    private var gradientPaint : Paint
    private var mPath: Path
    private var xAxisTotal = 0f
    private var yAxisTotal = 0f
    private lateinit var dataPoints : List<Int> //make data object to hold date & value
    private var highestProportionalValue : Int = 0
    private var highestValue : Int = 0
    private var lowestValue : Int = 0
    private var amountOfValues : Int = 0
    private var zeroY = 0F
    private var setSize = 3

    private var widthPerItem : Float = 0F
    private var heightPerValue : Float = 0F
    private var paddingForButtons : Float = 0f


    //todo compatability with negative numbers
    init {
        linePaint = Paint()
        mPath = Path()
        mBlackPaint = Paint()
        plotPaint = Paint()
        gradientPaint = Paint()
    }

    fun setDataPoints(data : List<Int>) {
        dataPoints = data
        dataPoints.highestNumber()?.let {highestNumber ->
            highestValue = highestNumber
        }
        dataPoints.lowestNumber()?.let { lowestNumber ->
            lowestValue = lowestNumber
        }
        if(highestValue - lowestValue == 0){
            //handle divide by zero error
        }
        amountOfValues = dataPoints.size
    }

    fun setGraduations(){

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        widthPerItem = ((width - paddingLeft - paddingRight) / amountOfValues).toFloat()
        heightPerValue = (((height - paddingTop - paddingBottom) / (highestValue - lowestValue)) * 0.9).toFloat() // 0.9 to make the height smaller
        zeroY = (height - paddingBottom).toFloat()
        initializePaint()
        drawGraphPlotAndLines(canvas)
        drawGraphFilling(canvas)
        /*
        xAxisTotal = (width / amountOfValues).toFloat()
        yAxisTotal = (height / (highestProportionalValue + (if(highestProportionalValue < 1) 1 else (highestProportionalValue / 10)))).toFloat()
        zeroY = (highestProportionalValue * (height / highestProportionalValue) + paddingTop).toFloat()
        initializePaint()

        drawGraphPlotAndLines(canvas)
        drawGraphFilling(canvas)
        drawAxis(canvas)
        //drawButtons(canvas)
        drawGraduations(canvas)

         */
    }

    private fun drawGraduations(canvas: Canvas?){
        val x = xAxisTotal * (dataPoints.size + 1).toFloat() - 20f
        var y : Float
        val textPaint = Paint()
        textPaint.color = Color.GRAY
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textSize = 40F
        for(i in 0..6){
            if(i != 0){
                y =  zeroY - 80f - ((height / 9) * i)
                canvas?.drawText(((highestValue / 6) * i).toString(), x, y, textPaint)
            }else{
                canvas?.drawText(0.toString(), x, zeroY - 80f, textPaint)
            }
        }
        canvas?.drawText(highestValue.toString(), x, zeroY - 80f - ((height / 9) * 7), textPaint)
    }

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
        mPath.moveTo(paddingLeft.toFloat() + 0f, zeroY - paddingForButtons)
        var iteration = 1
        dataPoints.forEach {
            mPath.lineTo(widthPerItem*iteration + paddingLeft, zeroY - (it.toFloat() * heightPerValue) - paddingForButtons)
            iteration ++
        }
        mPath.lineTo(widthPerItem * amountOfValues - paddingRight, zeroY - heightPerValue - paddingForButtons)
        canvas?.drawPath(mPath, gradientPaint)
    }


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

    private fun drawGraphPlotAndLines(canvas: Canvas?){
        var originX = widthPerItem + paddingLeft
        val originY = zeroY - paddingForButtons
        mPath.moveTo(0f + paddingLeft, originY)

        dataPoints.forEach{
            mPath.lineTo(originX , originY - (it * heightPerValue))
            canvas?.drawCircle(originX , originY - (it * heightPerValue), 10F, plotPaint)
            originX += widthPerItem
        }
        canvas?.drawPath(mPath, linePaint)
    }
}