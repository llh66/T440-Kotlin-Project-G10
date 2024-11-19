package com.example.project_g10

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project_g10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Check if this is the user's first time using the app
        val isFirstTime = sharedPreferences.getBoolean("is_first_time", true)

        if (isFirstTime) {
            // If first time, navigate to Enter Your Name screen
            val intent = Intent(this, EnterYourName::class.java)
            startActivity(intent)
        } else {
            // If not first time, navigate to Welcome Back screen
            val intent = Intent(this, WelcomeBack::class.java)
            startActivity(intent)
        }

        // Finish MainActivity 
        finish()
    }
}
