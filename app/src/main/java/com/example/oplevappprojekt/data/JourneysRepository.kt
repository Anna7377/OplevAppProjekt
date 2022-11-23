package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey

// s215722
interface JourneysRepository {
    fun addJourney(journey: Journey)
    fun getJourneys() : List<Journey>
    fun addIdea(idea: Idea)
    fun getIdeas(): List<Idea>
}