package com.example.oplevappprojekt.model

import java.time.LocalDate
import java.util.Date


data class Journey(
   val country: String,
    val date: Date,
   val img: Int

)

data class Idea(
    val image: String,
    val title: String,
    val desc: Date,
    val link: String,
    val category: String
)