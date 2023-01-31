package com.example.taskapp.ui.home.new_task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.ui.home.TaskModel

class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private var task: TaskModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater,container,false)

        initListeners()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openTask()
        checkTask()
    }

    private fun checkTask() {
        binding.btnSave.setOnClickListener{
            if (binding.etTitle.text.toString().isNotEmpty()) {
                if (task !== null){
                    updateTask()
                }else{
                    saveTask()
                }
            }else{
                binding.etTitle.error = getString(R.string.fell_place)

            }        }
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener{
            App.database.taskDao()?.insert(TaskModel(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            ))
            findNavController().navigateUp()
        }

    }

    @Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION")
    @SuppressLint("SetTextI18n")
    private fun openTask() {
        arguments?.let {
            val value = it.getSerializable("update-tasks")
            if (value != null) {
                task = value as TaskModel

                binding.etTitle.setText(task?.title.toString())
                binding.etDesc.setText(task?.desc.toString())
                if (task != null) {
                    binding.btnSave.text = getString(R.string.update)
                }else{
                    binding.btnSave.text = getString(R.string.save)
                }
            }
        }
    }

    private fun updateTask() {
        task?.title = binding.etTitle.text.toString()
        task?.desc = binding.etDesc.text.toString()
        task?.let{App.database.taskDao()?.update(it)}
        findNavController().navigateUp()
    }

    private fun saveTask() {
        binding.btnSave.setOnClickListener{
            App.database.taskDao()?.insert(
                TaskModel(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
            )
            findNavController().navigateUp()
        }
    }
}