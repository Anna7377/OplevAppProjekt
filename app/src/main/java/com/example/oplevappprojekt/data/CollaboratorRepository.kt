package com.example.oplevappprojekt.data
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.Auth
import com.example.oplevappprojekt.ViewModel.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList



val uid = Firebase.auth.currentUser?.uid.toString()
val currentCollection = Firebase.firestore
    .collection("users")
    .document(uid)
    .collection("coljourneys")

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
            "isPinned" to false,
                "img" to original["img"].toString()
            )
            currentCollection.document().set(journey)
        }
        return ret
    }

    suspend fun uncollab(orig: String) {
        currentCollection.document(orig).delete()
    }

    suspend fun showCol(orig: String): kotlin.collections.ArrayList<String> {
        val users = Firebase.firestore.collection("users").get().await()
        val usersRef = Firebase.firestore.collection("users")
        var cols = ArrayList<String>()
        for (i in 0..users.size()-1) {
            val uid = users.documents.get(i).id
            System.out.println(uid)
            val userobj = usersRef.document(uid).collection("coljourneys").
                whereEqualTo("originalJourneyID", orig).get().await().toObjects<Journey>()
            System.out.println(userobj)
            if (!userobj.isEmpty()) {
                val name = usersRef.document(uid).get().await().toObject<user>()?.name.toString()
                cols.add(name)
            }
        }
        if(cols.isEmpty()){
            cols = arrayListOf("Ingen Medarrangørere")
        }
        return cols
    }
}





