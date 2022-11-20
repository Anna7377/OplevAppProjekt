package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.model.Journey
import java.util.*

class HardcodedJourneysRepository : JourneysRepository {
    private val journeys = mutableListOf(
        Journey("Denmark", Date(1), R.drawable.image10),
    )
    override fun addJourney(journey: Journey) {
        journeys.add(journey)
    }

    override fun getJourneys(): List<Journey> {
        return journeys
    }
}