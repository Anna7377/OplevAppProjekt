package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.sql.Timestamp

//s215722
class HardcodedJourneysRepository {
    val uid = Firebase.auth.currentUser?.uid.toString()
    val journeys = Firebase.firestore.collection("journeys")
    var journeylist: ArrayList<Journey> = arrayListOf()


   suspend fun getJourneys(): ArrayList<Journey> {
        journeylist = journeys.whereEqualTo("userID", uid).get()
            .await()
            .toObjects<Journey>() as ArrayList<Journey>
       return withContext(Dispatchers.IO){ journeylist } }


fun addJourney(country: String, date: String){
    val journey = hashMapOf(
        "country" to country,
        "userID" to uid,
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis())
    )
    // journeylist.add(Journey(country = country, date=date, time = Timestamp((System.currentTimeMillis())), userID = uid))
  //   System.out.println(journeylist.get(1).country)
    journeys.document().set(journey) } }