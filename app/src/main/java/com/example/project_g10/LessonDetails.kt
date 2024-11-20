package com.example.project_g10

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g10.databinding.ActivityLessonDetailsBinding
import com.example.project_g10.models.Lesson


class LessonDetails : AppCompatActivity() {

    private lateinit var binding: ActivityLessonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val lesson = intent.getSerializableExtra("lesson") as Lesson

        binding.tvTitle.text = "${position + 1}. ${lesson.name}"
        binding.tvLength.text = "Length: ${lesson.length}"
        binding.tvDescription.text = lesson.descripton
        binding.btnWatchLesson.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(lesson.url))
            startActivity(intent)
        }
        if (lesson.isCompleted) {
            binding.btnMarkComplete.isEnabled = false
            binding.btnMarkComplete.text = "COMPLETED"
        }
        else{
            binding.btnMarkComplete.setOnClickListener {
                val returnIntent = Intent()
                returnIntent.putExtra("position", position)
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}