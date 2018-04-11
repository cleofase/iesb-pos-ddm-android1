package br.com.cleofase.loja

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"produtos-database").allowMainThreadQueries().build()

        var produtos = db!!.romDao().produtos()

        for (produto in produtos) {
            val myBotao = Button(this)
            myBotao.text = produto.nome

            container.addView(myBotao)

        }

    }






}
