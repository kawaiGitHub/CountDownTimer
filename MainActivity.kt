package com.test.myapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    private var remainingTime: Long = 0
    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startTime: Long = 10000

        val tv: TextView = findViewById(R.id.tv)
        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)
        val btnRestart: Button = findViewById(R.id.btnRestart)
        val btnReset: Button = findViewById(R.id.btnReset)
        tv.text = "${startTime / 1000}"

        btnStart.isEnabled = true
        btnStop.isEnabled = false
        btnRestart.isEnabled = false

        btnStart.setOnClickListener {
            timer = countDownTimer(startTime).start()

            btnStart.isEnabled = false
            btnStop.isEnabled = true
            btnRestart.isEnabled = false
        }

        btnStop.setOnClickListener {
            timer.cancel()

            btnStart.isEnabled = true
            btnStop.isEnabled = false
            btnRestart.isEnabled = true
        }

        btnRestart.setOnClickListener {
            timer = countDownTimer(remainingTime).start()

            btnStart.isEnabled = false
            btnStop.isEnabled = true
            btnRestart.isEnabled = false
        }

        btnReset.setOnClickListener {
            timer.cancel()
            remainingTime = 0
            tv.text = "${startTime / 1000}"

            btnStart.isEnabled = true
            btnStop.isEnabled = false
            btnRestart.isEnabled = false

            mp.stop()
            mp.release()
        }
    }

    private fun countDownTimer(st: Long): CountDownTimer {
        val tv: TextView = findViewById(R.id.tv)
        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        return object : CountDownTimer(st, 100) {
            override fun onTick(p0: Long) {
                //TODO("Not yet implemented")
                tv.text = "${p0 / 1000}"
                remainingTime = p0
            }

            override fun onFinish() {
                //TODO("Not yet implemented")
                tv.text = "終了！！"

                btnStart.isEnabled = false
                btnStop.isEnabled = false
                btnRestart.isEnabled = false

                mp = MediaPlayer.create(this@MainActivity, R.raw.bgm)
                mp.isLooping = true
                mp.start()
            }

        }

    }


}













