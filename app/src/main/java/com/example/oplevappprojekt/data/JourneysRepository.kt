package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Journey

interface JourneysRepository {
    fun addJourney(journey: Journey)
    fun getJourneys() : List<Journey>
}