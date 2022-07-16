package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val intent = Intent(this, EndActivity::class.java)
        startActivity(intent)

    }
}