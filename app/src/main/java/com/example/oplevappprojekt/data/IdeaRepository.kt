package com.example.oplevappprojekt.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

//s215718 & s213077

data class Idea(
    val category_ID : String = "",
    val description: String = "",
    )

interface IdeaRepo{
    suspend fun addIdea(journey: com.example.oplevappprojekt.data.Idea)
    suspend fun getIdea() : List<com.example.oplevappprojekt.data.Idea>
}

class IdeaRepository(private val firestore:FirebaseFirestore) : IdeaRepo{
    override suspend fun addIdea(Idea: com.example.oplevappprojekt.data.Idea){
        currentCollection().add(Idea)
    }

    override suspend fun getIdea(): List<Idea> {
        TODO("Not yet implemented")
    }

    private fun currentCollection(): CollectionReference =
        firestore.collection(IDEA_COLLECTION)

    companion object {
        private const val IDEA_COLLECTION = "Ideas"
    }
}