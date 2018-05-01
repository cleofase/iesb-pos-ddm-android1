package br.com.cleofase.loja

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_AddProdutos.setOnClickListener({goToAddProdutoAct()})


    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    fun updateUI() {
        val dbLoja: AppDatabase
        var produtos: Array<Produto>

        dbLoja = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"produtos-database").allowMainThreadQueries().build()
        produtos = dbLoja.romDao().produtos()
        container.removeAllViews()
        for (produto in produtos) {
            val produtoItem = Button(this)
            produtoItem.text = produto.nome
            //produtoItem.setTextColor(if (produto.naSacola == "true") {R.color.primary_dark_material_dark} else {R.color.error_color_material})
            produtoItem.setOnClickListener({changeBasketStatus(produto)})
            //Toast.makeText(applicationContext,produto.naSacola,Toast.LENGTH_SHORT).show()
            container.addView(produtoItem)
        }
    }

    fun goToAddProdutoAct() {
        val intent = Intent(this@MainActivity,CadastroProduto::class.java)
        startActivity(intent)
    }

    fun changeBasketStatus(produto: Produto) {
        val dbLoja: AppDatabase
        dbLoja = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"produtos-database").allowMainThreadQueries().build()
        produto.naSacola = if(produto.naSacola == "true") {"false"} else {"true"}
        dbLoja.romDao().updateProduto(produto)
        updateUI()
    }






}
