package br.com.cleofase.news.presenter

import android.arch.persistence.room.Room
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.RectF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import br.com.cleofase.news.R
import br.com.cleofase.news.dao.NewsDB
import kotlinx.android.synthetic.main.activity_new_detail.*

class NewDetailActivity : AppCompatActivity() {
    private lateinit var newsDB: NewsDB
    private var newId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail)

        newsDB = Room.databaseBuilder(applicationContext, NewsDB::class.java, "news-database").allowMainThreadQueries().build()
        newId = intent.getIntExtra("newId",0)
        btn_nwd_back.setOnClickListener { finish() }

        val new = newsDB.newDAO().newWithId(newId)
        new.let {
            lbl_title_new.text = new.title
            lbl_body_new.text = new.description
            lbl_date_new.text = new.date

            val bmpData = Base64.decode(new.photoData, Base64.DEFAULT)
            val originBmp = BitmapFactory.decodeByteArray(bmpData, 0, bmpData.size)

            originBmp?.let {
                img_photo_new.setImageBitmap(originBmp)
            }
        }

    }

}
