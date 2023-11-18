package com.example.activity_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activity_project.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        yavnyiButton()
        neYavnyiButton()
    }
    private fun yavnyiButton(){
        binding.startButton.setOnClickListener{
            if(isValid()) {
                val intent = Intent(this, TimerActivity::class.java)
                intent.putExtra(State.NAME.name,binding.editText.text.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, "You need input time", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun neYavnyiButton(){
        binding.sendButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, binding.editText.text.toString())
                intent.type = "text/plain"
                val choseIntent = Intent.createChooser(intent, "Title")
                startActivity(choseIntent)
            } else {
                Toast.makeText(this, "You need input time", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValid() = !binding.editText.text.isNullOrBlank()
}
