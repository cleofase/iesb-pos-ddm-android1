package br.com.cleofase.radar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.ScheduledExecutorService
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.concurrent.timerTask
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    private lateinit var scannerHandler: Handler
    private lateinit var scannerRunnable: Runnable

    private var currentAngle = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scannerHandler = Handler()
    }

    override fun onResume() {
        super.onResume()

        scannerRunnable = Runnable {
            scan()
            scannerHandler.postDelayed(scannerRunnable,100)
        }
        scannerHandler.postDelayed(scannerRunnable,100)
    }

    override fun onPause() {
        super.onPause()
        scannerHandler.removeCallbacks(scannerRunnable)
    }


    private fun scan() {
        currentAngle = currentAngle + (PI/120).toFloat()
        scanner.angle = currentAngle
    }
}
