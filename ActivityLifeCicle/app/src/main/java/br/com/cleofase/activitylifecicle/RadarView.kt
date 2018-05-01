package br.com.cleofase.activitylifecicle

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RadarView: View {
    private val paint: Paint = Paint()
    private val rect = RectF(0f, 0f, 50f, 50f)
    private val oval = RectF(0f, 60f, 50f, 110f)
    private val arcOval = RectF(50f, 130f, 350f, 430f)
    private val path = Path()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw Rect
//        paint.color = Color.BLUE
//        paint.strokeWidth = 1f
//        paint.style = Paint.Style.FILL
//        canvas.drawRect(rect, paint)

        // Draw Oval
        val rectFace = RectF(0f,0f,this.width.toFloat(),this.height.toFloat())
        paint.color = Color.RED
        paint.strokeWidth = 3f
        paint.style = Paint.Style.STROKE
        canvas.drawOval(rectFace, paint)

        // Draw Arc
//        paint.color = Color.DKGRAY
//        paint.strokeWidth = 2f
//        paint.style = Paint.Style.FILL_AND_STROKE
//        canvas.drawArc(arcOval, 180f, 90f, true, paint)

        // Draw Line
//        paint.color = Color.MAGENTA
//        paint.strokeWidth = 5f
//        paint.style = Paint.Style.STROKE
//        canvas.drawLine(0f, 440f, 250f, 600f, paint)

        // Draw Path
//        paint.color = Color.LTGRAY
//        paint.strokeWidth = 5f
//        paint.style = Paint.Style.STROKE
//
//        path.moveTo(50f, 600f)
//        path.lineTo(50f, 950f)
//        path.lineTo(200f, 950f)
//        path.lineTo(200f, 800f)
//        path.lineTo(350f, 800f)
//
//        canvas.drawPath(path, paint)
    }

}