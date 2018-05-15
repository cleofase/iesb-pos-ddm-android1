package br.com.cleofase.news.presenter

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cleofase.news.R
import br.com.cleofase.news.dao.NewsDB
import br.com.cleofase.news.entity.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    lateinit var newsDB: NewsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        newsDB = Room.databaseBuilder(applicationContext,NewsDB::class.java,"news-database").allowMainThreadQueries().build()
        btn_sgu_signUp.setOnClickListener{trySignUpUser()}
    }

    private fun trySignUpUser() {
        val emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}".toRegex()
        val passwordRegex = "[A-Z0-9a-z._%+-]{6,}".toRegex()
        val users: Array<User>

        if (!emailRegex.matches(txt_sgu_email.text.toString())) {
            Toast.makeText(this, "Informe um email válido!", Toast.LENGTH_LONG).show()
            return
        }
        users = newsDB.userDAO().users(txt_sgu_email.text.toString())
        if (users.count() > 0) {
            Toast.makeText(this, "Já existe um cadastro com o email informado!", Toast.LENGTH_LONG).show()
            return
        }
        if (!passwordRegex.matches(txt_sgu_password.text.toString())) {
            Toast.makeText(this, "Informe um password válido!", Toast.LENGTH_LONG).show()
            return
        }

        if (!txt_sgu_password.text.toString().equals(txt_sgu_re_password.text.toString())) {
            Toast.makeText(this, "Redigite o mesmo password informado!", Toast.LENGTH_LONG).show()
            return
        }

        var newUser = User()
        newUser.email = txt_sgu_email.text.toString()
        newUser.password = txt_sgu_password.text.toString()
        newUser.current = 1
        newsDB.userDAO().insertUser(newUser)

        val currentUsers = newsDB.userDAO().currentUsers()
        if (currentUsers.count() > 0) {
            val currentUser = currentUsers.first()
            val intent = Intent(this@SignupActivity, UserNewsActivity::class.java)
            intent.putExtra("ownerId", currentUser.id)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Falha no cadastro!", Toast.LENGTH_LONG).show()
            return
        }
    }
}
