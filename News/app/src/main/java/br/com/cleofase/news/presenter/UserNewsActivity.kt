package br.com.cleofase.news.presenter

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cleofase.news.R
import br.com.cleofase.news.dao.NewsDB
import br.com.cleofase.news.entity.New
import br.com.cleofase.news.entity.NewFactory
import br.com.cleofase.news.view.NewItem
import kotlinx.android.synthetic.main.activity_user_news.*

class UserNewsActivity : AppCompatActivity() {
    private var ownerId: Int = 0
    private lateinit var newsDB: NewsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_news)
        newsDB = Room.databaseBuilder(applicationContext,NewsDB::class.java,"news-database").allowMainThreadQueries().build()

        btn_user_detail.setOnClickListener { goToUserDetailAct() }
        val bannerImagesIndex: Array<Int> = arrayOf(R.drawable.banner_baiatodosossantos, R.drawable.banner_fortesaomarcelo, R.drawable.banner_morrosaopaulo)
        banner.photoResourcesIndex = bannerImagesIndex
        banner.transitionTime = 3000

        ownerId = intent.getIntExtra("ownerId", 0)
        var news = newsDB.newDAO().news(ownerId)
        if (news.count() == 0) {
            NewFactory.generateSomeNews(ownerId, newsDB)
            news = newsDB.newDAO().news(ownerId)
        }

        for (new in news) {
            val newItem = NewItem(new.title, new.description, new.date, new.photoData, this)
            newItem.setOnClickListener { goToNewDetail(new) }
            container.addView(newItem)
        }
    }

    private fun goToNewDetail(new: New) {
        val intent = Intent(this@UserNewsActivity, NewDetailActivity::class.java)
        intent.putExtra("newId",new.id)
        startActivity(intent)
    }

    private fun goToUserDetailAct() {
        val intent = Intent(this@UserNewsActivity, UserDetailActivity::class.java)
        startActivity(intent)
    }
}
