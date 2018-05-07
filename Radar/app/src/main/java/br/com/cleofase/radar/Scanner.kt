package br.com.cleofase.radar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.lang.Math.PI
import java.util.*
import java.util.concurrent.ScheduledExecutorService
import kotlin.concurrent.fixedRateTimer
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.concurrent.schedule

class Scanner: View {
    constructor(context: Context?) : super(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    private var cursorAngle: Float = 0f

    public var angle: Float
        set(newAngle: Float) {
            cursorAngle = newAngle
            invalidate()
        }
        get() {
            return cursorAngle
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = minOf(this.width/2,this.height).toFloat()
        val center = PointF(this.width.toFloat()/2,this.height.toFloat())

        this.setBackgroundColor(Color.BLACK)

        drawScreen(center, radius, canvas)
        drawCursor(center,radius,cursorAngle,canvas)
    }

    private fun drawScreen(centerBaseScanner: PointF, screenRadius: Float, canvas: Canvas) {
        val slice = 12
        var paint = Paint()
        paint.strokeWidth = 3f
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE

        drawGridArc(centerBaseScanner, screenRadius, paint, canvas)

        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE

        drawGridArc(centerBaseScanner, screenRadius, paint, canvas)

        paint.strokeWidth = 1f


        for (dist in 1..slice) {
            val distRadius = dist.toFloat() * screenRadius / slice
            drawGridArc(centerBaseScanner, distRadius, paint, canvas)
        }

        for (arc in 1..slice) {
            val angleArc: Float = PI.toFloat() + arc.toFloat() * PI.toFloat()/slice.toFloat()
            val arcPoint = PointF(centerBaseScanner.x + screenRadius * cos(angleArc),centerBaseScanner.y + screenRadius * sin(angleArc))
            canvas.drawLine(centerBaseScanner.x, centerBaseScanner.y, arcPoint.x, arcPoint.y, paint)
            canvas.drawText("${-90 + ((angleArc - PI) * 180/PI).roundToInt()}º", arcPoint.x - if (cos(angleArc) < 0f) {40f} else {0f} , arcPoint.y, paint)
//            val radialPath = Path()
//            radialPath.moveTo(arcPoint.x, arcPoint.y)
//            radialPath.lineTo(centerBaseScanner.x, centerBaseScanner.y)
//            canvas.drawPath(radialPath,paint)
//            canvas.drawTextOnPath("${-90 + ((angleArc - PI) * 180/PI).roundToInt()}º", radialPath, -100f, -100f, paint)
//            setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        }
    }

    private fun drawGridArc(center: PointF, radius: Float, paint: Paint, canvas: Canvas) {
        val rectGridArc = RectF(center.x - radius, center.y - radius, center.x + radius, center.y + radius)
        canvas.drawArc(rectGridArc, 180f, 360f, true, paint)
    }

    private fun drawCursor(center: PointF, radius: Float, angle: Float, canvas: Canvas, harm: Int = 8) {
        if (harm == 0) {return}

        var paint = Paint()
        paint.strokeWidth = 10f
        paint.color = Color.GREEN
        paint.alpha = 32 * harm - 1
        paint.style = Paint.Style.STROKE

        val angleCursor = PI + angle - ((8 - harm) * PI / 120).toFloat()
        Log.d("Angulo","angleCursor: ${angleCursor}")
        Log.d("Angulo","Center: ${center.x} e ${center.y} - Radius: ${radius}")
        val arcPoint = PointF(center.x + radius * cos(angleCursor).toFloat(), center.y - radius * sin(angleCursor).absoluteValue.toFloat())
        canvas.drawLine(center.x, center.y, arcPoint.x, arcPoint.y, paint)
        drawCursor(center, radius, angle, canvas, harm - 1)
    }

}