package com.example.oplevappprojekt.model

import java.time.LocalDate
import java.util.Date

//s215722
data class Journey(
   val country: String,
    val date: String,
   val img: Int,
   var IdeaList: MutableList<Idea> = mutableListOf<Idea>()
)

data class Idea(
    val title: String,
    val desc: String,
    var IdeaList: MutableList<Idea> = mutableListOf<Idea>()
)

// s215718 & s213370
data class Categories(
    val desc: String,
    val JPEG: Int,
    var IdeaList: MutableList<Idea> = mutableListOf<Idea>()
)