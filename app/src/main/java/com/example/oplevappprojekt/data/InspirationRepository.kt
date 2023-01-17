package com.example.oplevappprojekt.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

// s215722
data class InspirationText(
    val text: String = " ",
    val userID: String = " "
)

class InspirationRepository {

     suspend fun read() : String {
         val text: InspirationText?
         val uid = Firebase.auth.currentUser?.uid.toString()
         text = currentCollection().document(uid).get().await()
             .toObject<InspirationText>()
         var outputtext = ""
         if (text != null) {
             outputtext=text.text
         }
             return withContext(Dispatchers.IO){ outputtext } }

    fun update(insp: String, ID: String) {
        val temp = hashMapOf(
            "text" to insp,
        "userID" to ID)
        currentCollection().document(ID).set(temp)
            }


    private fun currentCollection(): CollectionReference =
        Firebase.firestore.collection(INSPIRATION_COLLECTION)

    companion object Insp {
        private const val INSPIRATION_COLLECTION = "inspirationtext"
    }
}

