package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


data class Journey(
    val country: String = "",
    val tripName: String = ""
)

interface JourneyRepo {
    suspend fun addJourney(journey: com.example.oplevappprojekt.data.Journey)
    suspend fun getJourneys(): List<com.example.oplevappprojekt.data.Journey>
}

class JourneyRepository(private val firestore: FirebaseFirestore) : JourneyRepo {

    override suspend fun addJourney(journey: com.example.oplevappprojekt.data.Journey) {
        currentCollection().add(journey)
    }

    override suspend fun getJourneys(): List<com.example.oplevappprojekt.data.Journey> {
        TODO("Not yet implemented")
    }

    private fun currentCollection(): CollectionReference =
        firestore.collection(JOURNEY_COLLECTION)

    companion object {
        private const val JOURNEY_COLLECTION = "destinations"
    }


}