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
        val coljourneylist: ArrayList<Journey>
        val colIDs: ArrayList<String> = arrayListOf()
        val unpinID: ArrayList<String> = arrayListOf()
// get pinned journeys
        val journeydocs = journeys.whereEqualTo("userID", uid).whereEqualTo("isPinned", true).get()
            .await()
        journeylist = journeydocs.toObjects<Journey>() as ArrayList<Journey>
        for (i in 0..journeydocs.size() - 1) {
            IDs.add(journeydocs.documents.get(i).id)
            journeylist.get(i).JourneyID = IDs.get(i)
        }
// get non-pinned journeys
        val journeydocs2 = journeys.whereEqualTo("userID", uid).whereEqualTo("isPinned", false).get()
            .await()
        val journeylist2 = journeydocs2.toObjects<Journey>() as ArrayList<Journey>
        for (i in 0..journeylist2.size - 1) {
            unpinID.add(journeydocs2.documents.get(i).id)
            journeylist2.get(i).JourneyID = unpinID.get(i)
            journeylist.add(journeylist2.get(i))
        }
// get collaborated journeys
        val coljourneys = Firebase.firestore.collection("users")
            .document(uid).collection("coljourneys")
        val coldocs = coljourneys.get().await()
        coljourneylist = coldocs.toObjects<Journey>() as ArrayList<Journey>
        for (i in 0..coldocs.size() - 1) {
            val journ = coljourneylist.get(i).originalJourneyID
            colIDs.add(coldocs.documents.get(i).id)
            if (journeys.document(journ).get().await().exists()) {
                coljourneylist.get(i).JourneyID = colIDs.get(i)
                journeylist.add(coljourneylist.get(i))
            } else {
                coljourneys.document(colIDs.get(i)).delete()
                colIDs.removeAt(i)
            }
        }
        return withContext(Dispatchers.IO) { journeylist }
    }


fun addJourney(country: String, date: String){
    val journey = hashMapOf(
        "country" to country,
        "userID" to uid,
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis()),
        "isPinned" to false

    )
    journeys.document().set(journey) }

fun editJourney(journeyID: String, date: String, country: String, pinVal : Boolean){
    val journey = hashMapOf(
        "country" to country,
        "userID" to uid,
        "date" to date,
        "time" to Timestamp(System.currentTimeMillis()),
        "isPinned" to pinVal
    )
    journeys.document(journeyID).set(journey)
}

    fun deleteJourney(ID: String){
        journeys.document(ID).delete()
    }

}