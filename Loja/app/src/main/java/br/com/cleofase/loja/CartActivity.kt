package br.com.cleofase.loja

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    fun updateUI() {
        val dbLoja: AppDatabase
        var produtos: Array<Produto>

        dbLoja = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"produtos-database").allowMainThreadQueries().build()
        produtos = dbLoja.romDao().produtosNaSacola()
        container.removeAllViews()
        for (produto in produtos) {
            val produtoItem = ItemView(produto.nome, produto.descricao, "R$ ${produto.preco}", produto.foto, produto.naSacola == "true", this)
            container.addView(produtoItem)
        }
    }
}
