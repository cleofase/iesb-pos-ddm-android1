package br.com.cleofase.loja

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Base64
import android.view.View

class ItemView: View {
    private val padding: Float = 8f
    var name: String = ""
    var description: String = ""
    var price: String = ""
    var tumbnail: String = ""
    var inCart: Boolean = false

    constructor(itemName: String, itemDescription: String, itemPrice: String, itemTumbnail: String, itemInCart: Boolean, context: Context?): super(context, null) {
        this.name = itemName
        this.description = itemDescription
        this.price = itemPrice
        this.tumbnail = itemTumbnail
        this.inCart = itemInCart

    }
    constructor(context: Context?,attrs: AttributeSet?): super(context, attrs, 0)
    constructor(context: Context?,attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

    }

    private fun drawRectItem(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.LTGRAY
        paint.alpha = 64
        paint.style = Paint.Style.FILL
        val rectItem = RectF(padding, padding, this.width.toFloat() - padding, this.height.toFloat() - padding)
        canvas.drawRect(rectItem, paint)
    }

    private fun drawName(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 40f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE

        canvas.drawText(name, this.height.toFloat(), this.height.toFloat()/4, paint)
    }

    private fun drawDescription(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 40f
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE

        canvas.drawText(description, this.height.toFloat(), this.height.toFloat()/2, paint)
    }

    private fun drawPrice(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 40f
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE

        canvas.drawText(price, this.height.toFloat(), this.height.toFloat() - 2 * padding, paint)
    }

    private fun drawStatus(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 40f
        paint.textAlign = Paint.Align.RIGHT
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE

        canvas.drawText(if (this.inCart) {"No Carrinho"} else {""}, this.width.toFloat() - 2 * padding, this.height.toFloat() - 2 * padding, paint)
    }

    private fun drawTumbnail(canvas: Canvas) {
        val paint = Paint()
        val tumbnailWidth = this.height.toFloat() - 2 * padding
        val tumbnailHeight = tumbnailWidth
        drawRectTumbnail(RectF(padding, padding, tumbnailWidth, tumbnailHeight), canvas)
        if (tumbnail.length == 0) {return}
        val photoData = Base64.decode(tumbnail, Base64.DEFAULT)
        val photoBmp = BitmapFactory.decodeByteArray(photoData, 0, photoData.size)
        photoBmp.let {
            val resizeMatrix = Matrix()
            val widthRatio = tumbnailWidth / photoBmp.width.toFloat()
            val heightRatio = tumbnailHeight / photoBmp.height.toFloat()
            resizeMatrix.postScale(widthRatio, heightRatio)
            val tumbnailBmp = Bitmap.createBitmap(photoBmp, 0, 0, photoBmp.width, photoBmp.height, resizeMatrix, true)
            canvas.drawBitmap(tumbnailBmp, padding, padding, paint)
        }
    }

    private fun drawRectTumbnail(rectTumbnail: RectF, canvas: Canvas) {
        val paint = Paint()
        paint.strokeWidth = 1f
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.LTGRAY
        canvas.drawRect(rectTumbnail, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, 200)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas!!)
        drawRectItem(canvas!!)
        drawName(canvas!!)
        drawDescription(canvas!!)
        drawPrice(canvas!!)
        drawStatus(canvas!!)
        drawTumbnail(canvas!!)
    }
}