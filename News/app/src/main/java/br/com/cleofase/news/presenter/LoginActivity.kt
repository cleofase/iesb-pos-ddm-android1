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

class LoginActivity : AppCompatActivity() {
    lateinit var newsDB: NewsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        newsDB = Room.databaseBuilder(applicationContext,NewsDB::class.java,"news-database").allowMainThreadQueries().build()

        btn_login.setOnClickListener {tryLogin()}
        btn_signUp.setOnClickListener { goToSignUpAct() }
        btn_resetPass.setOnClickListener { goToResetPassAct() }
    }

    private fun tryLogin() {
        val emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}".toRegex()
        val passwordRegex = "[A-Z0-9a-z._%+-]{6,}".toRegex()
        val users: Array<User>

        if (!emailRegex.matches(txt_email.text.toString())) {
            Toast.makeText(this, "Informe um email válido!", Toast.LENGTH_LONG).show()
            return
        }
        if (!passwordRegex.matches(txt_password.text.toString())) {
            Toast.makeText(this, "Informe um password válido!", Toast.LENGTH_LONG).show()
            return
        }

        users = newsDB.userDAO().users(txt_email.text.toString())
        if (users.count() > 0) {
            val user = users.first()
            if (user.password.equals(txt_password.text.toString())) {
                user.current = 1
                newsDB.userDAO().updateUser(user)
                val intent = Intent(this@LoginActivity, UserNewsActivity::class.java)
                intent.putExtra("ownerId", user.id)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email ou Senha incorretos!", Toast.LENGTH_LONG).show()
                return
            }
        } else {
            Toast.makeText(this, "Email ou Senha incorretos!", Toast.LENGTH_LONG).show()
            return
        }
    }

    private fun goToSignUpAct() {
        val intent = Intent(this@LoginActivity, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun goToResetPassAct() {
        val intent = Intent(this@LoginActivity, ResetPassActivity::class.java)
        startActivity(intent)
    }
}
