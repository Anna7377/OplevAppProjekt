package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea

interface IdeasRepository {
    fun getIdea() : List<Idea>
    fun addIdea(idea: Idea)
}