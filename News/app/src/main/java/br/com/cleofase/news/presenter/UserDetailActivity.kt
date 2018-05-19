package br.com.cleofase.news.presenter

import android.Manifest
import android.arch.persistence.room.Room
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.widget.Toast
import br.com.cleofase.news.R
import br.com.cleofase.news.dao.NewsDB
import br.com.cleofase.news.entity.User
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.io.ByteArrayOutputStream

class UserDetailActivity : AppCompatActivity() {
    private val CAMERA = 1
    private val REQUEST_PERMISSION = 1

    private var userId: Int = 0
    private lateinit var newsDB: NewsDB
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        userId = intent.getIntExtra("userId", 0)
        btn_usd_save.setOnClickListener { trySaveUser() }
        btn_usd_logout.setOnClickListener{ logoutUser() }
        img_usd_photo.setOnClickListener { tryCapturePhoto() }
        newsDB = Room.databaseBuilder(applicationContext, NewsDB::class.java, "news-database").allowMainThreadQueries().build()
        user = newsDB.userDAO().userWithId(userId)
        txt_usd_name.setText(user.name)
        txt_usd_password.setText(user.password)
        txt_usd_re_password.setText(user.password)
        txt_usd_enrolling.setText(user.enrolling)
        txt_usd_phone.setText(user.phone)
        txt_usd_email.setText(user.email)

        val photoData = Base64.decode(user.photo, Base64.DEFAULT)
        val photoBmp = BitmapFactory.decodeByteArray(photoData, 0, photoData.size)
        photoBmp?.let {
            img_usd_photo.setImageBitmap(photoBmp)
        }
    }

    private fun tryCapturePhoto() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION)
            return
        }
        intentToCaptureImage()
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

    private fun intentToCaptureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageStream = ByteArrayOutputStream()
        val imageData = data!!.extras!!.get("data") as Bitmap
        img_usd_photo.setImageBitmap(imageData)
        imageData.compress(Bitmap.CompressFormat.PNG, 100, imageStream)
        user.photo = Base64.encodeToString(imageStream.toByteArray(), Base64.DEFAULT)
    }

    private fun trySaveUser() {
        val passwordRegex = "[A-Z0-9a-z._%+-]{6,}".toRegex()

        if (!(txt_usd_name.text.toString().count() > 0)) {
            Toast.makeText(this, "Informe o nome!", Toast.LENGTH_LONG).show()
            return
        }

        if (!passwordRegex.matches(txt_usd_password.text.toString())) {
            Toast.makeText(this, "Informe um password válido!", Toast.LENGTH_LONG).show()
            return
        }

        if (!txt_usd_password.text.toString().equals(txt_usd_re_password.text.toString())) {
            Toast.makeText(this, "Redigite o mesmo password informado!", Toast.LENGTH_LONG).show()
            return
        }

        if (!(txt_usd_enrolling.text.toString().count() > 0)) {
            Toast.makeText(this, "Informe a matrícula!", Toast.LENGTH_LONG).show()
            return
        }

        if (!(txt_usd_phone.text.toString().count() > 0)) {
            Toast.makeText(this, "Informe o número do telefone!", Toast.LENGTH_LONG).show()
        }

        user.name = txt_usd_name.text.toString()
        user.enrolling = txt_usd_enrolling.text.toString()
        user.password = txt_usd_password.text.toString()
        user.phone = txt_usd_phone.text.toString()
        newsDB.userDAO().updateUser(user)
        finish()
    }

    private fun logoutUser() {
        user.current = 0
        newsDB.userDAO().updateUser(user)
        val intent = Intent(this@UserDetailActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
