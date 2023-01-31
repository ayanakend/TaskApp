package com.example.taskapp.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter(private var onLongClick: (Int)-> Unit, private val onClick: (TaskModel) -> Unit) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var taskList = arrayListOf<TaskModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    fun getTask(position: Int): TaskModel {
        return taskList[position]
    }

    inner class ViewHolder(private var binding: ItemTaskBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(taskModel: TaskModel) {
            binding.tvTitleItem.text = taskModel.title
            binding.tvDescItem.text = taskModel.desc

            changeColor(adapterPosition, binding)

            itemView.setOnClickListener {
                onClick(taskModel)
            }

            itemView.setOnLongClickListener{
                onLongClick(adapterPosition)

                return@setOnLongClickListener true
            }
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

    private fun changeColor(position: Int, binding: ItemTaskBinding) {
        if(position % 2 != 0) {
            binding.itemContainer.setBackgroundColor(Color.BLACK)
            binding.tvTitleItem.setTextColor(Color.WHITE)
            binding.tvDescItem.setTextColor(Color.WHITE)
            binding.viewString.setBackgroundColor(Color.WHITE)
        }
    }
}