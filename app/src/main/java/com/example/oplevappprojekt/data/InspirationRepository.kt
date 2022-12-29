package com.example.oplevappprojekt.data

import android.content.ContentValues.TAG
import android.provider.DocumentsContract
import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.D
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

data class InspirationText(
    val text: String = " "
)

class InspirationRepository() {
    var D = InspirationText("")
    fun read(): String {
        val uid = Firebase.auth.currentUser?.uid.toString()
        val docRef = currentCollection().document(uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                  D = document.toObject()!!
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    return D.text
}

    fun update(insp: String) {
        val temp = hashMapOf(
            "text" to insp)
        currentCollection().document(
            Firebase.auth.currentUser
                ?.uid.toString()).set(temp)
            }


    private fun currentCollection(): CollectionReference =
        Firebase.firestore.collection(INSPIRATION_COLLECTION)

    companion object insp {
        private const val INSPIRATION_COLLECTION = "inspirationtext"
    }
}

