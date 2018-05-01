package br.com.cleofase.activitylifecicle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Log.d("Activity Life Cicle","First Activity - onCreate")
        btn_nextActivity.setOnClickListener({goToSecondActivity()})

    }

    private fun goToSecondActivity() {
        val intent = Intent(this@FirstActivity,SecondActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity Life Cicle","First Activity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity Life Cicle","First Activity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity Life Cicle","First Activity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity Life Cicle","First Activity - onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Activity Life Cicle","First Activity - onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity Life Cicle","First Activity - onDestroy")
    }
}
