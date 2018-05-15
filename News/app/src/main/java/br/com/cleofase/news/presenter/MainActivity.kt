package br.com.cleofase.news.presenter

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cleofase.news.R
import br.com.cleofase.news.dao.NewsDB
import br.com.cleofase.news.entity.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val newsDB: NewsDB
        var currentUsers: Array<User>

        newsDB = Room.databaseBuilder(applicationContext,NewsDB::class.java,"news-database").allowMainThreadQueries().build()
        currentUsers = newsDB.userDAO().currentUsers()

        if (currentUsers.count() > 0) {
            val currentUser = currentUsers.first()
            val intent = Intent(this@MainActivity, UserNewsActivity::class.java)
            intent.putExtra("ownerId", currentUser.id)
            startActivity(intent)
        } else {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
