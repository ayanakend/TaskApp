package com.example.taskapp.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.viewpager.widget.ViewPager
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardBinding
import com.example.taskapp.databinding.FragmentOnBoardPageBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

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