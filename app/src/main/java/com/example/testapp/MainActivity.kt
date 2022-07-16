package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val fileName = "score.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rules = "Try to tap the most moles in 30 seconds"
        binding.tvInfo.text = rules
        val path = binding.root.context.filesDir
        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory,fileName)
        val isNewFile = file.createNewFile()
        if(isNewFile){
            file.writeText("0")
        }


        binding.tvNumScore.text = file.readLines()[0]


        binding.btnStart.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            this.startActivity(intent)
        }
    }
}