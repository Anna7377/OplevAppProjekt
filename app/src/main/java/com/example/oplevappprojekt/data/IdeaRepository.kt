package com.example.oplevappprojekt.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

//s215718

data class Idea(
    val categoryID : String = "",
    val description: String = "",
    val journeyID : String = "",
    val title : String = ""
    )

interface IdeaRepo{
    suspend fun addIdea(journey: com.example.oplevappprojekt.data.Idea)
    suspend fun getIdea() : List<com.example.oplevappprojekt.data.Idea>
}

class IdeaRepository(private val firestore:FirebaseFirestore) : IdeaRepo{
    override suspend fun addIdea(Idea: com.example.oplevappprojekt.data.Idea){
        currentCollection().add(Idea)
    }
    val userIdeas: ArrayList<com.example.oplevappprojekt.data.Idea> = arrayListOf()
    override suspend fun getIdea(): List<Idea> {
        currentCollection().get().addOnSuccessListener { documents ->
            for (document in documents){
                if(document.get("Document ID")?.equals(Firebase.auth.currentUser?.uid)==true){
                    userIdeas.add(document.toObject())
                }
        }
    }
        return userIdeas
    }

    private fun currentCollection(): CollectionReference =
        firestore.collection(IDEA_COLLECTION)

    companion object {
        private const val IDEA_COLLECTION = "Ideas"
    }
}