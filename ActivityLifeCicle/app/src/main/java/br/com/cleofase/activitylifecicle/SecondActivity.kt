package br.com.cleofase.activitylifecicle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("Activity Life Cicle","Second Activity - onCreate")
        btn_back.setOnClickListener({goToBackActivity()})
    }

    private fun goToBackActivity() {
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity Life Cicle","Second Activity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity Life Cicle","Second Activity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity Life Cicle","Second Activity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity Life Cicle","Second Activity - onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Activity Life Cicle","Second Activity - onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity Life Cicle","Second Activity - onDestroy")
    }
}
