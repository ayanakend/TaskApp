package com.example.taskapp.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskapp.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BoardAdapter(childFragmentManager, this::onNextClick, this::onScipClick)
        binding.vpBoard.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.vpBoard)

    }

    private fun onNextClick() {
        binding.vpBoard.currentItem += 1
    }

    private fun onScipClick() {
        binding.vpBoard.currentItem = 3
    }
}