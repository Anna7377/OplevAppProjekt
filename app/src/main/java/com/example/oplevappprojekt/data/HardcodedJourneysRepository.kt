package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

//s215722
class HardcodedJourneysRepository {
    val journeys = Firebase.firestore.collection("journeys")
    val journeylist: ArrayList<com.example.oplevappprojekt.ViewModel.Journey> = arrayListOf(
        com.example.oplevappprojekt.ViewModel.Journey(
            "Denmark",
            date = "25/08/02",
            "XYZ"
        )
    )

    fun getJourneys(): ArrayList<com.example.oplevappprojekt.ViewModel.Journey> {
        journeys.orderBy("time").get().addOnSuccessListener { documents ->
            for (document in documents) {
                if (document.get("userID")?.equals(Firebase.auth.currentUser?.uid) == true) {
                    journeylist.add(document.toObject())
                } } }
        return journeylist
    }
fun addJourney(country: String, date: String){
    val journey = hashMapOf(
        "country" to country,
        "userID" to Firebase.auth.currentUser?.uid.toString(),
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis())
    )
    journeys.document().set(journey)
}


}