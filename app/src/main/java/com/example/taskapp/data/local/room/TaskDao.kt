package com.example.taskapp.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp.ui.home.TaskModel


@Dao
interface TaskDao {
    @Insert
    fun insert(task: TaskModel?)

    @Query("SELECT * FROM TaskModel")
    fun getAllTasks(): List<TaskModel?>?

    @Query("SELECT * FROM TaskModel ORDER BY title ASC")
    fun getAllTasksByAlphabetAz(): List<TaskModel?>?

    @Query("SELECT * FROM TaskModel ORDER BY title DESC")
    fun getAllTasksByAlphabetZa(): List<TaskModel?>?

    @Query("SELECT * FROM TaskModel ORDER BY id DESC")
    fun getAllTasksByDate(): List<TaskModel?>?

    @Delete
    fun delete(task: TaskModel?)
}