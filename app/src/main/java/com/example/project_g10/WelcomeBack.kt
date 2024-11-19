package com.example.project_g10

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project_g10.databinding.ActivityWelcomeBackBinding

class WelcomeBack : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBackBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Get the user's name and display the welcome message
        val userName = sharedPreferences.getString("user_name", "User")
        binding.welcomeMessage.text = "Welcome back, $userName"

        // Handle the Continue button click
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LessonsList::class.java)
            startActivity(intent)
        }

        // Handle the Reset button click
        binding.btnReset.setOnClickListener {
            // Clear all data in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            // Redirect to Enter Your Name screen
            val intent = Intent(this, EnterYourName::class.java)
            startActivity(intent)

            // Finish this activity so it's not in the back stack
            finish()
        }
    }
}
