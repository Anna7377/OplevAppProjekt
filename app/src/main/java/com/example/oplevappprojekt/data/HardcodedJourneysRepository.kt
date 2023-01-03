package com.example.oplevappprojekt.data

import android.app.DownloadManager.Query
import com.example.oplevappprojekt.ViewModel.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentSnapshot
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
    val IDs : ArrayList<String> = arrayListOf()


   suspend fun getJourneys(): ArrayList<Journey> {

       /*  journeylist = journeys.whereEqualTo("userID", uid).get()
            .await()
            .toObjects<Journey>() as ArrayList<Journey>
        */
       val journeydocs = journeys.whereEqualTo("userID", uid).get()
           .await()
       journeylist = journeydocs.toObjects<Journey>() as ArrayList<Journey>
       for(i in 0..journeydocs.size()-1) {
       IDs.add(journeydocs.documents.get(i).id)
       journeylist.get(i).JourneyID=IDs.get(i)
       System.out.println(journeylist.get(i).JourneyID)}

       return withContext(Dispatchers.IO){ journeylist } }


fun addJourney(country: String, date: String){
    val journey = hashMapOf(
        "country" to country,
        "userID" to uid,
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis())
    )
    journeys.document().set(journey) }

fun editJourney(journeyID: String, date: String, country: String){
    val journey = hashMapOf(
        "country" to country,
        "userID" to uid,
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis())
    )
    journeys.document(journeyID).set(journey)
}

    fun deleteJourney(ID: String){
        journeys.document(ID).delete()
    }

}