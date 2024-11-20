package com.example.project_g10

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g10.databinding.ActivityWelcomeBackBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

        val gson = Gson()
        val completionStatusJson = sharedPreferences.getString("COMPLETION_STATUS", null)
        val completionStatus: List<Boolean>
        if (completionStatusJson != null) {
            val type = object : TypeToken<List<Boolean>>() {}.type
            completionStatus = gson.fromJson(completionStatusJson, type)
        }
        else {
            completionStatus = listOf()
        }
        if (completionStatus.size == 5) {
            val completedCount = completionStatus.count { it }
            binding.progressInfo.text = """
                You've completed ${(completedCount / 5.0 * 100).toInt()}% of the course
                
                Lessons completed: ${completedCount}
                Lessons remaining: ${5 - completedCount}
            """.trimIndent()
        }
        else {
            binding.progressInfo.text = "ERROR: retrieving progress info failed"
        }

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
