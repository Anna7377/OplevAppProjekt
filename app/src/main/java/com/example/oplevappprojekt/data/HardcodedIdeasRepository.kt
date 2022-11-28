package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey

class HardcodedIdeasRepository : IdeasRepository {
    private val ideas = mutableListOf(Idea("Skagen", "Husk mad!"), Idea("Bakken", "Rabat om Onsdagen"))

    override fun getIdea(): List<Idea> {
        TODO("Not yet implemented")
    }

    override fun addIdea(idea: Idea) {
        TODO("Not yet implemented")
    }

}