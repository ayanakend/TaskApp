@file:Suppress("DEPRECATION")

package com.example.taskapp.ui.onboard

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.taskapp.R

class BoardAdapter(fm: FragmentManager,
                   private var listenerNext: () -> Unit,
                   private var listenerSkip: () -> Unit
                   ) : FragmentStatePagerAdapter(fm) {

    private val listBoarding = arrayOf(BoardModel(
        R.drawable.ic_img1,
        "To-do list!",
        "Here you can write down something important or make a schedule for tomorrow:)",
        false, R.color.bg_board1),
    BoardModel(
        R.drawable.ic_img2,
        "Share your crazy idea ^_^",
        "You can easily share with your report, list or schedule and it's convenient",
        false, R.color.bg_board2),
    BoardModel(
        R.drawable.ic_img3,
        "Flexibility",
        "Your note with you at home, at work, even at the resort",
        true, R.color.bg_board3)
    )

    override fun getCount(): Int {
        return listBoarding.size
    }

    override fun getItem(position: Int): Fragment {
        val data = bundleOf("onBoard" to listBoarding[position])
        val fragment = OnBoardPageFragment(listenerNext, listenerSkip)
        fragment.arguments = data
        return fragment
    }
}