package com.example.taskapp.ui.onboard

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardPageBinding


class OnBoardPageFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener{
            findNavController().navigate(R.id.navigation_home)
        }
    }

    private fun initViews() {

        arguments.let{

            val data = it?.getSerializable("onBoard") as BoardModel
            binding.imgBoard.setImageResource(data.img)
            binding.tvTitle.text = data.title
            binding.tvDesc.text = data.description

            binding.btnSkip.isVisible = data.isLast == false
            binding.btnNext.isVisible = data.isLast == false
            binding.btnStart.isVisible = data.isLast == true
        }
    }
}