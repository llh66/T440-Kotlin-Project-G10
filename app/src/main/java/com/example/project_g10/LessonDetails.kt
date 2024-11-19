package com.example.project_g10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g10.databinding.ActivityLessonDetailsBinding

class LessonDetails : AppCompatActivity() {

    private lateinit var binding: ActivityLessonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}