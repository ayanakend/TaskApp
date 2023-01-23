package com.example.taskapp.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.utils.Preferences

class ProfileFragment: Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var binding: FragmentProfileBinding

    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->

            Glide.with(this).load(imageUri.toString()).into(binding.circleImageView)
            preferences.saveImage(imageUri.toString())
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.circleImageView.setOnClickListener {
            getContent.launch("image/*")
        }

        preferences = Preferences(requireContext())
        saveChanges()
        checkChanges()

        return binding.root
    }

    private fun checkChanges() {
        if (preferences.getName() != ""){
            binding.etName.setText(preferences.getName())
        }
    }

    private fun saveChanges() {
        binding.etName.addTextChangedListener {
            preferences.saveName(binding.etName.text.toString())
        }
        if (preferences.getImage() != ""){
            Glide.with(this).load(preferences.getImage()).into(binding.circleImageView)
        }
    }
}