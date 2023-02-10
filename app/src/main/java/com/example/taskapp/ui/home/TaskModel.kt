package com.example.taskapp.ui.home

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var imgUrl: String? = null,
    var title: String? = null,
    var desc: String? = null,
): Serializable
