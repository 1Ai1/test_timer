package com.example.activity_project
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.activity_project.databinding.ActivityTimerBinding
import java.util.Locale
import android.content.Intent

class TimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerBinding

    private var seconds: Int = 0
    private var running: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.let {
            val name = it.getString(State.NAME.name)

            val userInput = it.getString(Intent.EXTRA_TEXT)!!.toInt()
            seconds = userInput
        }


        with(binding){
            startButton.setOnClickListener {
                Log.e(this.javaClass.name, ">>> startButton.setOnClickListener ")
                startClick()
            }
        }
        binding.textView.text = String.format(
            Locale.getDefault(),
            "%02d:%02d",
            seconds/ 60,
            seconds% 60
        )


        savedInstanceState?.let{
            seconds = it.getInt(State.SECONDS.name)
            running = it.getBoolean(State.RUNNING.name)
        }

        runTimer()
    }

    private fun runTimer(){
        Log.e(this.javaClass.name, ">>> runTimer ")
        if(running) {
            object : CountDownTimer(seconds.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.textView.text = String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        (millisUntilFinished / 1000) / 60,
                        (millisUntilFinished / 1000) % 60
                    )
                }

                // Callback function, fired
                // when the time is up
                override fun onFinish() {
                    binding.textView.setText("00:00")
                }
            }.start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(State.SECONDS.name, seconds)
        super.onSaveInstanceState(outState)
    }
    private fun startClick() {
        running = true
        runTimer()
    }
}
enum class State{
    RUNNING, SECONDS, WAS_RUNNING, NAME
}