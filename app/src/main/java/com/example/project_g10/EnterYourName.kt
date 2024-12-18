package com.example.project_g10

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g10.databinding.ActivityEnterYourNameBinding
import com.google.gson.Gson

class EnterYourName : AppCompatActivity() {

    private lateinit var binding: ActivityEnterYourNameBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterYourNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        binding.btnContinue.setOnClickListener {
            val userName = binding.Name.text.toString()

            if (userName.isNotEmpty()) {
                // Save the user's name and mark that it's not the first time
                val editor = sharedPreferences.edit()
                editor.putString("user_name", userName)
                editor.putBoolean("is_first_time", false)

                val completionStatus = listOf(false, false, false, false, false)
                val gson = Gson()
                val completionStatusJson = gson.toJson(completionStatus)
                editor.putString("COMPLETION_STATUS", completionStatusJson)

                editor.apply()

                // Redirect to the Lessons List screen
                val intent = Intent(this, LessonsList::class.java)
                startActivity(intent)

                // Finish this activity
                finish()

            } else {
                // Show an error if the name is empty
                binding.Name.error = "Please enter your name"
            }
        }
    }
}
