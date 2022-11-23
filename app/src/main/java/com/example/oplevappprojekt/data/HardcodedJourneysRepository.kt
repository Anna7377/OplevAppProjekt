package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey
import java.util.*
import kotlin.collections.ArrayList

//s215722
class HardcodedJourneysRepository : JourneysRepository {
    val idea: Idea = Idea("my idea", "Hi")
    val myideas = arrayListOf(idea)
    private val journeys = mutableListOf(
        Journey("Denmark", Date(1), R.drawable.image9, myideas ),
        Journey("Iran", Date(2), R.drawable.image8, myideas)
    )
    override fun addJourney(journey: Journey) {
        journeys.add(journey)
    }

    override fun getJourneys(): List<Journey> {
        return journeys
    }

    override fun getIdeas(): List<Idea> {
        return myideas
    }

    override fun addIdea(idea: Idea) {
        myideas.add(idea)
    }
}