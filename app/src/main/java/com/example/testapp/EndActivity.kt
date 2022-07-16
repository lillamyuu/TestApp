package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.databinding.ActivityEndBinding
import java.io.File

class EndActivity : AppCompatActivity() {
    lateinit var binding: ActivityEndBinding
    val fileName = "score.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val path = binding.root.context.filesDir
        val letDirectory = File(path, "LET")
        val file = File(letDirectory,fileName)

        binding.tvBestUserScoreNum.text = file.readLines()[0]

        binding.btnMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnStartAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}