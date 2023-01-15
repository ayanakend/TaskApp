package com.example.taskapp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var taskList = arrayListOf<TaskModel>()


     fun addTask(taskModel: TaskModel) {
        taskList.add(taskModel)
         Log.e("ololo","addTask" + taskModel.title )
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemTaskBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(taskModel: TaskModel) {
            binding.tvTitleItem.text = taskModel.title
            binding.tvDescItem.text = taskModel.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}