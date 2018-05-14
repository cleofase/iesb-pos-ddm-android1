package br.com.cleofase.loja

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ItemView: View {
    private val padding: Float = 8f
    var name: String = ""
    var description: String = ""
    var price: String = ""
    var inCart: Boolean = false

    constructor(itemName: String, itemDescription: String, itemPrice: String, itemInCart: Boolean, context: Context?): super(context, null) {
        this.name = itemName
        this.description = itemDescription
        this.price = itemPrice
        this.inCart = itemInCart
    }
    constructor(context: Context?,attrs: AttributeSet?): super(context, attrs, 0)
    constructor(context: Context?,attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

    }

    private fun drawRectItem(canvas: Canvas) {
        val paint = Paint()
        paint.color = if (this.inCart) {Color.GRAY} else {Color.LTGRAY}
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

    private fun drawTumbnail(canvas: Canvas) {


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
    }
}