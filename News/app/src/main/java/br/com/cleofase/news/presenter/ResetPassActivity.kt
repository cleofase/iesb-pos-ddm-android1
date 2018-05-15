package br.com.cleofase.news.presenter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cleofase.news.R
import kotlinx.android.synthetic.main.activity_reset_pass.*

class ResetPassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pass)
        btn_rsp_resetPass.setOnClickListener{finish()}
    }

}
