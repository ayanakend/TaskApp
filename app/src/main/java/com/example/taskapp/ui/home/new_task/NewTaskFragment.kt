package com.example.taskapp.ui.home.new_task

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.data.remote.firestore.FirestoreUtils
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.extensoins.loadImage
import com.example.taskapp.ui.home.TaskModel
import com.example.taskapp.utils.Preferences

class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private var task: TaskModel? = null
    private var imgUri: String? = null

    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            imgUri = imageUri.toString()
            binding.imgNewTask.loadImage(imageUri.toString())
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openTask()
        checkTask()
    }

    private fun checkTask() {
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                if (task !== null) {
                    updateTask()
                } else {
                    saveTask()
                }
            } else {
                binding.etTitle.error = getString(R.string.fell_place)

            }
        }
    }

    private fun initListeners() {
        binding.imgNewTask.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            App.database.taskDao()?.insert(
                TaskModel(
                    imgUri = imgUri,
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
            )
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
                } else {
                    binding.btnSave.text = getString(R.string.save)
                }
            }
        }
    }

    private fun updateTask() {
        task?.title = binding.etTitle.text.toString()
        task?.desc = binding.etDesc.text.toString()
        task?.let { App.database.taskDao()?.update(it) }
        findNavController().navigateUp()
    }

    private fun saveTask() {
        binding.btnSave.setOnClickListener {
            saveDataToRoom()
            saveDataToFirestore()
            findNavController().navigateUp()
        }
    }


    private fun saveDataToFirestore() {
        val task = TaskModel(
            imgUri = imgUri,
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )

        FirestoreUtils().fireStore.collection("User1")
            .add(task)
            .addOnSuccessListener {
                Log.d(TAG, "Added document with ID ${it.id}")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error adding document $exception")
            }
    }

    private fun saveDataToRoom() {
        App.database.taskDao()?.insert(
            TaskModel(
                imgUri = imgUri,
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
        )

    }
}
