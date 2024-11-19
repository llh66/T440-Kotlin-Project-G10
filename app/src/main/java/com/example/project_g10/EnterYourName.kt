package com.example.project_g10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g10.databinding.ActivityEnterYourNameBinding

class EnterYourName : AppCompatActivity() {

    private lateinit var binding: ActivityEnterYourNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterYourNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}