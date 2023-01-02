package com.example.oplevappprojekt.data

import android.app.Activity
import android.content.ContentValues.TAG
import android.provider.DocumentsContract
import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.D
import com.example.oplevappprojekt.sites.Inspiration
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

data class InspirationText(
    val text: String = " ",
    val ID: String = " "
)

class InspirationRepository() {


     fun read() : String {
         var text: InspirationText? = InspirationText("Z", "")
         val uid = Firebase.auth.currentUser?.uid.toString()
         System.out.println(uid)
         GlobalScope.launch(Dispatchers.IO) {
            val text2 = currentCollection().document(uid).get().await()
                 .toObject<InspirationText>()
             text=text2
             delay(3000L)
         }
         return text.toString()
     }

    fun update(insp: String, ID: String) {
        val temp = hashMapOf(
            "text" to insp,
        "userID" to ID)
        currentCollection().document(ID).set(temp)
            }


    private fun currentCollection(): CollectionReference =
        Firebase.firestore.collection(INSPIRATION_COLLECTION)

    companion object insp {
        private const val INSPIRATION_COLLECTION = "inspirationtext"
    }
}

