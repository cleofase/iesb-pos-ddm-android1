package br.com.cleofase.humanface

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class HumanFaceView: View {
    private var paint: Paint = Paint()
    constructor(context: Context): this(context,null)
    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr) {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radiusFace: Float = minOf(this.width,this.height).toFloat()/2
        val centerFace: PointF = PointF(this.width.toFloat()/2,this.height.toFloat()/2)

        val centerLeftEye = PointF(centerFace.x + 0.5f * radiusFace* cos(5*PI/4).toFloat(),centerFace.y + 0.5f * radiusFace* sin(5*PI/4).toFloat())
        val centerRightEye = PointF(centerFace.x + 0.5f * radiusFace* cos(7*PI/4).toFloat(),centerFace.y + 0.5f * radiusFace* sin(7*PI/4).toFloat())

        val eyeWidth = 0.25f * radiusFace
        val eyeHeight = 0.25f * radiusFace

        val centerLeftNose = PointF(centerFace.x - 0.1f * radiusFace,centerFace.y)
        val centerRightNose = PointF(centerFace.x + 0.1f * radiusFace,centerFace.y)
        val noseWidth = 0.1f * radiusFace
        val noseHeight = 0.1f * radiusFace

        val centerMouth = PointF(centerFace.x,centerFace.y + 0.25f * radiusFace * sin(PI/2).toFloat())
        val mouthWidth = radiusFace
        val mouthHeight = 0.5f * radiusFace

        drawFace(centerFace,radiusFace,canvas)
        drawEye(centerLeftEye,eyeWidth,eyeHeight,canvas)
        drawEye(centerRightEye,eyeWidth,eyeHeight,canvas)
        drawNose(centerLeftNose,noseWidth,noseHeight,canvas)
        drawNose(centerRightNose,noseWidth,noseHeight,canvas)
        drawMouth(centerMouth,mouthWidth,mouthHeight,canvas)
    }

    private fun drawFace(center: PointF, radius: Float, canvas: Canvas) {
        var paint: Paint = Paint()
        paint.color = Color.YELLOW
        paint.strokeWidth = 1f
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(center.x,center.y,radius,paint)
    }

    private fun drawEye(center: PointF,width: Float, heigth: Float, canvas: Canvas) {
        var paint: Paint = Paint()
        paint.color = Color.WHITE
        paint.strokeWidth = 1f
        paint.style = Paint.Style.FILL_AND_STROKE
        drawAmazingOval(center,width,heigth,paint,canvas)
    }

    private fun drawNose(center: PointF,width: Float, heigth: Float, canvas: Canvas) {
        var paint: Paint = Paint()
        paint.color = Color.WHITE
        paint.strokeWidth = 3f
        paint.style = Paint.Style.FILL_AND_STROKE
        drawAmazingOval(center,width,heigth,paint,canvas)
    }

    private fun drawAmazingOval(center: PointF,width: Float, heigth: Float, paint: Paint, canvas: Canvas) {
        val topLeftX = center.x - width/2
        val topLeftY = center.y - heigth/2
        val bottomRightX = center.x + width/2
        val bottomRightY = center.y + width/2
        val rectOfOval = RectF(topLeftX,topLeftY,bottomRightX,bottomRightY)

        canvas.drawOval(rectOfOval,paint)
    }

    private fun drawMouth(center: PointF, width: Float, heigth: Float, canvas: Canvas) {
        val topLeftX = center.x - width/2
        val topLeftY = center.y - heigth/2
        val middleRightX = center.x + width/2
        val middleRightY = center.y
        val bottomRightX = center.x + width/2
        val bottomRightY = center.y + heigth/2
        val rectUpperLip = RectF(center.x - width/2,center.y - heigth/2,center.x + width/2,center.y + heigth/2)
        val rectBottomLip = RectF(center.x - width/2,center.y - heigth/4,center.x + width/2,center.y + heigth/4)

        var paint = Paint()
        paint.color = Color.WHITE
        paint.strokeWidth = 3f
        paint.style = Paint.Style.FILL_AND_STROKE

        val path = Path()
        path.addArc(rectBottomLip,0f,180f)
        path.addArc(rectUpperLip,0f,180f)
        path.fillType = Path.FillType.EVEN_ODD

        canvas.drawPath(path,paint)


    }

}