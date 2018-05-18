package br.com.cleofase.news.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Base64
import android.view.View

class NewItem: View {
    private val padding: Float = 8f
    var title: String = ""
    var body: String = ""
    var date: String = ""
    var tumbnail: String = ""

    constructor(title: String, body: String, date: String, tumbnail: String, context: Context?): super(context, null) {
        this.title = title
        this.body = body
        this.date = date
        this.tumbnail = tumbnail
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

    }

    private fun drawRectItem(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.RED
        paint.alpha = 64
        paint.style = Paint.Style.FILL
        val rectItem = RectF(padding, padding, this.width.toFloat() - padding, this.height.toFloat() - padding)
        canvas.drawRect(rectItem, paint)
    }

    private fun drawTitle(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 40f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE

        canvas.drawText(title, this.height.toFloat(), this.height.toFloat()/4, paint)
    }

    private fun drawBody(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 32f
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        canvas.drawText(body, this.height.toFloat(), this.height.toFloat()/2, paint)
    }

    private fun drawDate(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 32f
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        canvas.drawText(date, this.height.toFloat(), this.height.toFloat() - 2f * padding, paint)
    }

    private fun drawTumbnailBackRect(tumbnailRect: RectF, canvas: Canvas) {
        val paint = Paint()
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 1f
        paint.color = Color.RED

        canvas.drawRect(tumbnailRect, paint)
    }

    private fun drawTumbnail(canvas: Canvas) {
        val paint = Paint()
        val bmpData = Base64.decode(tumbnail, Base64.DEFAULT)
        val originBmp = BitmapFactory.decodeByteArray(bmpData, 0, bmpData.size)
        val tumbnailWidth = this.height.toFloat() - 2f * padding
        val tumbnailHeight = tumbnailWidth

        drawTumbnailBackRect(RectF(padding, padding, tumbnailWidth, tumbnailHeight), canvas)

        originBmp?.let {
            val widthRatio = tumbnailWidth/originBmp.width.toFloat()
            val heightRatio = tumbnailHeight/originBmp.height.toFloat()
            val resizeMatrix = Matrix()
            resizeMatrix.postScale(widthRatio,heightRatio)
            val resizedBmp = Bitmap.createBitmap(originBmp, 0, 0, originBmp.width, originBmp.height, resizeMatrix, true)
            canvas.drawBitmap(resizedBmp, padding, padding, paint)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, 200)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas!!)
        drawRectItem(canvas!!)
        drawTitle(canvas!!)
        drawBody(canvas!!)
        drawDate(canvas!!)
        drawTumbnail(canvas!!)
    }

}