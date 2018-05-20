package br.com.cleofase.loja

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAddToCart.setOnClickListener {goToAddProdutoAct()}
        btnCart.setOnClickListener {goToCartAct()}


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
            Log.d("Debug","Produto: ${produto.nome} imagem: ${produto.foto}")
            val produtoItem = ItemView(produto.nome, produto.descricao, "R$ ${produto.preco}", produto.foto, produto.naSacola == "true", this)
            produtoItem.setOnClickListener({changeBasketStatus(produto)})
            container.addView(produtoItem)
        }
    }

    fun goToAddProdutoAct() {
        val intent = Intent(this@MainActivity,CadastroProduto::class.java)
        startActivity(intent)
    }

    fun goToCartAct() {
        val intent = Intent(this@MainActivity, CartActivity::class.java)
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
