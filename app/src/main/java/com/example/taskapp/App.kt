package com.example.taskapp

import android.app.Application
import androidx.room.Database
import androidx.room.Room

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, Database::class.java,
            "database").allowMainThreadQueries().build()
    }
    companion object{
        lateinit var database: Database
    }
}
