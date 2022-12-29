package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject

// s213370 & s215718

data class Ideas(
    val categoryID: String = "",
    val description: String = "",
    val journeyID: String = "",
    val title: String = ""
)

interface IdeaRepo {
    suspend fun addIdea(category: com.example.oplevappprojekt.data.Ideas)
    suspend fun getIdea(): List<com.example.oplevappprojekt.data.Ideas>
}

class IdeaRepository(private val firestore: FirebaseFirestore) : IdeaRepo {
    override suspend fun addIdea(ideas: Ideas) {
        currentCollection().add(ideas)
    }

    val userIdeas: ArrayList<com.example.oplevappprojekt.data.Ideas> = arrayListOf()
    override suspend fun getIdea(): List<Ideas> {
        currentCollection().get().addOnSuccesListener { documents ->
            for (document in documents) {
                if (document.get("Document ID")?.equals(Firebase.auth.currentUser?.uid) == true) {
                    userIdeas.add(document.toObject())
                }
            }

        }
        return userIdeas
    }

    private fun currentCollection(): CollectionReference = firestore.collection(IDEA_COLLECTION)

    companion object {
        private const val IDEA_COLLECTION = "categories"
    }

}
