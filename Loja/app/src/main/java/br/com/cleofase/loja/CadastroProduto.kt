package br.com.cleofase.loja

import android.Manifest
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_produto.*
import java.io.ByteArrayOutputStream

class CadastroProduto : AppCompatActivity() {
    private val CAMERA = 1
    private val REQUEST_PERMISSION = 1
    private var imageDataStr: String = ""

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"produtos-database").allowMainThreadQueries().build()

        btnCadastrar.setOnClickListener({cadastrar()})
        imageView.isClickable = true
        imageView.setOnClickListener({tryCaptureImage()})
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            REQUEST_PERMISSION -> {
                if (permissions.contains(Manifest.permission.CAMERA) && grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                    intentToCaptureImage()
                }
            }
        }
    }

    private fun tryCaptureImage() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION)
            return
        }
        intentToCaptureImage()
    }

    private fun intentToCaptureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageStream = ByteArrayOutputStream()
        val imageData = data!!.extras!!.get("data") as Bitmap
        imageView.setImageBitmap(imageData)
        imageData.compress(Bitmap.CompressFormat.PNG, 100, imageStream)
        imageDataStr = Base64.encodeToString(imageStream.toByteArray(), Base64.DEFAULT)
    }

    fun cadastrar() {
        if (txtNome.text.toString().length == 0) {
            Toast.makeText(this, "Informe um nome para o produto", Toast.LENGTH_LONG).show()
            return
        }
        if (txtDescricao.text.toString().length == 0) {
            Toast.makeText(this, "Informe uma descrição para o produto", Toast.LENGTH_LONG).show()
            return
        }
        if (txtPreco.text.toString().length == 0) {
            Toast.makeText(this, "Informe um preço para o produto", Toast.LENGTH_LONG).show()
            return
        }
        var produto = Produto()
        produto.nome = txtNome.text.toString()
        produto.descricao = txtDescricao.text.toString()
        produto.preco = txtPreco.text.toString().toFloat()
        produto.foto = imageDataStr
        produto.naSacola = "false"


        db!!.romDao().insertProduto(produto)
        finish()
    }

}
