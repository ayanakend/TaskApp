package com.example.taskapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.utils.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.navigate(R.id.authFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.profileFragment,
                R.id.newTaskFragment
            )
        )

        if (!Preferences(applicationContext).isBoardingShowed()){
            navController.navigate(R.id.onBoardFragment)
        }

        val listWithOutBottomNav = setOf(R.id.newTaskFragment, R.id.onBoardFragment, R.id.authFragment)
        val listWithOutActBar = setOf(R.id.onBoardFragment, R.id.authFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (listWithOutBottomNav.contains(destination.id)) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
            if (listWithOutActBar.contains(destination.id)) {
                supportActionBar?.hide()
            }else{
                supportActionBar?.show()
            }
        }

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}