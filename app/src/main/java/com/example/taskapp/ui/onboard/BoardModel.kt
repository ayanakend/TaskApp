package com.example.taskapp.ui.onboard

data class BoardModel(
     var img: Int,
     var title: String,
     var description: String,
     var isLast : Boolean,
     var bg : Int
) : java.io.Serializable
