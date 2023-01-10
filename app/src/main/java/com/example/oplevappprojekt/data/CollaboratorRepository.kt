package com.example.oplevappprojekt.data
import com.example.oplevappprojekt.ViewModel.Auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

data class colJourney(
    val originaljourneyID: String = "",
    val country: String = "",
    val date: String = "",
    val time: Date = Date(System.currentTimeMillis()) )

val uid = Firebase.auth.currentUser?.uid.toString()
val currentCollection = Firebase.firestore
    .collection("users")
    .document(uid)
    .collection("colJourneys")

class CollaboratorRepository {

    suspend fun addJourney(orig: String): String {
        val original = Firebase.firestore.collection("journeys").document(orig).get().await()
        var ret = "Journey does not exist"
        if (original.exists()) {
            ret = "Succesfully added"

            val journey = hashMapOf(
                "originalJourneyID" to orig,
                "country" to original["country"].toString(),
                "date" to original["date"].toString(),
                "time" to Timestamp(System.currentTimeMillis()),
            "isPinned" to false
            )
            currentCollection.document().set(journey)
        }
        return ret
    }

    suspend fun uncollab(orig: String) {
        val del = currentCollection.whereEqualTo("originaljourneyID", orig).get().await()
        val id = del.documents.get(0).id
        currentCollection.document(id).delete()
    }

    suspend fun showCol(orig: String): kotlin.collections.ArrayList<String> {
        val users = Firebase.firestore.collection("users").get().await()
        val coljourneys = Firebase.firestore.collection("users")
        val cols = ArrayList<String>()
        for (i in 1..users.size()) {
            val uid = users.documents.get(i).id
            val userobj = coljourneys.document(uid).get().await().toObject<Auth>()
            val obj = coljourneys.document(uid)
                .collection("coljourneys")
                .whereEqualTo("originaljourneyID", orig).get().await().toObjects<colJourney>()
            if (!obj.isEmpty()) {
                cols.add(userobj?.userName.toString())
            }
        }
        return cols
    }
}



