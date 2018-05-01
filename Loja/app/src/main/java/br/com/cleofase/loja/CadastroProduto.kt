package br.com.cleofase.loja

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_cadastro_produto.*

class CadastroProduto : AppCompatActivity() {

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"produtos-database").allowMainThreadQueries().build()

        btnCadastrar.setOnClickListener({cadastrar()})







    }

    fun cadastrar() {
        var produto = Produto()
        produto.nome = txtNome.text.toString()
        produto.descricao = txtDescricao.text.toString()
        produto.preco = 0.0f //txtPreco.text.toString().toFloat()
        produto.naSacola = "false"


        db!!.romDao().insertProduto(produto)
        Log.d("teste", "O nome do produto é: ${db!!.romDao().produtos().last().nome}")
        finish()

//        val intent = Intent(this@CadastroProduto,MainActivity::class.java)
//        startActivity(intent)

    }



}
