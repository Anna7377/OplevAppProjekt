package com.example.oplevappprojekt.model

import java.time.LocalDate
import java.util.Date

//s215722
data class Journey(
   val country: String,
    val date: String,
   val img: Int,
   var IdeaList: MutableList<Idea> = mutableListOf<Idea>(),
   var isPinned: Boolean = false
)

data class Idea(
    val title: String,
    val desc: String
)