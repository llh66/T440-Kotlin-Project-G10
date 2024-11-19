package com.example.project_g10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g10.databinding.ActivityWelcomeBackBinding

class WelcomeBack : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LessonsList::class.java)
            startActivity(intent)
        }
    }
}