package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


data class Journey(
    val country: String = "",
    val Date: String = "",
    val userID: String
)

interface JourneyRepo {
    suspend fun addJourney(journey: com.example.oplevappprojekt.data.Journey)
    suspend fun getJourneys(): List<com.example.oplevappprojekt.data.Journey>
}

class JourneyRepository(private val firestore: FirebaseFirestore) : JourneyRepo {

    override suspend fun addJourney(journey: com.example.oplevappprojekt.data.Journey) {
        currentCollection().add(journey)
    }
val userJourneys: ArrayList<com.example.oplevappprojekt.data.Journey> = arrayListOf()

    override suspend fun getJourneys(): List<com.example.oplevappprojekt.data.Journey> {
        currentCollection().get().addOnSuccessListener { documents ->
            for (document in documents) {
                if (document.get("Document ID")?.equals(Firebase.auth.currentUser?.uid) == true) {
                    userJourneys.add(document.toObject())
                } } }
        return userJourneys }


    private fun currentCollection(): CollectionReference =
       firestore.collection(JOURNEY_COLLECTION)

    companion object {
        private const val JOURNEY_COLLECTION = "journeys"
    }


}
