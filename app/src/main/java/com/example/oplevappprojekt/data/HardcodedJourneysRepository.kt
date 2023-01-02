package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp

//s215722
class HardcodedJourneysRepository {
    val uid = Firebase.auth.currentUser?.uid.toString()
    val journeys = Firebase.firestore.collection("journeys")
    var journeylist: ArrayList<Journey> = arrayListOf(
        Journey(
            "Denmark",
            date = "25/08/02",
            userID = "XYZ"
        )
    )

   suspend fun getJourneys(): ArrayList<Journey> {
        journeylist = journeys.whereEqualTo("userID", uid).get()
            .await()
            .toObjects<Journey>() as ArrayList<Journey>
       return journeylist }

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