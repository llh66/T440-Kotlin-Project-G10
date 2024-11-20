package com.example.project_g10

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_g10.adapters.LessonAdapter
import com.example.project_g10.databinding.ActivityLessonsListBinding
import com.example.project_g10.models.Lesson
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonsList : AppCompatActivity(), ClickDetectorInterface {

    private lateinit var binding: ActivityLessonsListBinding

    private lateinit var lessonAdapter: LessonAdapter

    private val lessonsList: MutableList<Lesson> = mutableListOf()

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var completionStatus: MutableList<Boolean>

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val completionStatusJson = sharedPreferences.getString("COMPLETION_STATUS", null)
        if (completionStatusJson != null) {
            val type = object : TypeToken<MutableList<Boolean>>() {}.type
            completionStatus = gson.fromJson(completionStatusJson, type)
        }
        else {
            completionStatus = mutableListOf()
        }
        if (completionStatus.size == 5) {
            lessonsList.add(Lesson("Introduction to Programming and Computer Science", "2 hr", getString(R.string.lesson_01_description), getString(R.string.lesson_01_url), completionStatus[0]))
            lessonsList.add(Lesson("Learn Python", "4 hr 27 min", getString(R.string.lesson_02_description), getString(R.string.lesson_02_url), completionStatus[1]))
            lessonsList.add(Lesson("SQL Tutorial", "4 hr 21 min", getString(R.string.lesson_03_description), getString(R.string.lesson_03_url), completionStatus[2]))
            lessonsList.add(Lesson("Learn JavaScript", "3 hr 27 min", getString(R.string.lesson_04_description), getString(R.string.lesson_04_url), completionStatus[3]))
            lessonsList.add(Lesson("C Programming Tutorial", "3 hr 47 min", getString(R.string.lesson_05_description), getString(R.string.lesson_05_url), completionStatus[4]))

            lessonAdapter = LessonAdapter(lessonsList, this)
            binding.rvLessons.adapter = lessonAdapter
            binding.rvLessons.layoutManager = LinearLayoutManager(this)
            binding.rvLessons.addItemDecoration(
                DividerItemDecoration(
                    this,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        else {
            val snackbar = Snackbar.make(binding.root, "ERROR: retrieving progress info failed", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    override fun showLessonDetail(position: Int) {
        val intent = Intent(this, LessonDetails::class.java)
        intent.putExtra("lesson", lessonsList[position])
        intent.putExtra("position", position)

        startForResult.launch(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val position = result.data!!.getIntExtra("position", 0)
            lessonsList[position].isCompleted = true
            lessonAdapter.notifyDataSetChanged()

            completionStatus[position] = true
            val editor = sharedPreferences.edit()
            val completionStatusJson = gson.toJson(completionStatus)
            editor.putString("COMPLETION_STATUS", completionStatusJson)
            editor.apply()
        }
    }
}