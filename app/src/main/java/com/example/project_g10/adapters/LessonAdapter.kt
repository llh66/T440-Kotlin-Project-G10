package com.example.project_g10.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.project_g10.ClickDetectorInterface
import com.example.project_g10.databinding.RowLessonBinding
import com.example.project_g10.models.Lesson

class LessonAdapter (val lessonsList: MutableList<Lesson>, val clickInterface: ClickDetectorInterface) : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowLessonBinding) : RecyclerView.ViewHolder (binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lesson = this.lessonsList[position]

        holder.binding.tvRowNo.text = (position + 1).toString()
        holder.binding.tvLessonInfo.text = """
            ${lesson.name}
            Length: ${lesson.length}
        """.trimIndent()
        holder.binding.imgCompleted.isVisible = lesson.isCompleted

        holder.binding.root.setOnClickListener {
            clickInterface.showLessonDetail(position)
        }
    }
}