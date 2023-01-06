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
    val IDs : ArrayList<String> = arrayListOf()

   suspend fun getJourneys(): ArrayList<Journey> {
       var coljourneylist: ArrayList<Journey> = arrayListOf()
       val colIDs : ArrayList<String> = arrayListOf()

       val journeydocs = journeys.whereEqualTo("userID", uid).get()
           .await()
       journeylist = journeydocs.toObjects<Journey>() as ArrayList<Journey>
       for(i in 0..journeydocs.size()-1) {
       IDs.add(journeydocs.documents.get(i).id)
       journeylist.get(i).JourneyID=IDs.get(i) }

       val coldocs =  Firebase.firestore.collection("users")
           .document(uid).collection("coljourneys").get().await()
      coljourneylist = coldocs.toObjects<Journey>() as ArrayList<Journey>
       for(i in 0..coldocs.size()-1){
           colIDs.add(coldocs.documents.get(i).id)
           coljourneylist.get(i).JourneyID=colIDs.get(i)
           journeylist.add(coljourneylist.get(i))
       }


       return withContext(Dispatchers.IO){ journeylist } }

    suspend fun isCollaborated(ID: String) : Boolean{
        val db = Firebase.firestore.collection("users")
            .document(uid).collection("coljourneys").document(ID).get().await()
        var iscol = false
        if(db.exists()){
            iscol = true
        }
        return iscol
    }


    fun addJourney(country: String, date: String){
    val journey = hashMapOf(
        "country" to country,
        "userID" to uid,
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis()),
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