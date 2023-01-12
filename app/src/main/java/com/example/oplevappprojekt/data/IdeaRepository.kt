package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.Idea
import com.example.oplevappprojekt.ViewModel.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.sql.Timestamp

// s213370 & s215718

class IdeaRepository {

    val jid = Firebase.auth.currentUser?.uid.toString()
    val ideas = Firebase.firestore.collection("ideas")
    var idealist: ArrayList<Idea> = arrayListOf()
    val cid = Firebase.auth.currentUser?.uid.toString()


    suspend fun getIdeas(): ArrayList<Idea> {

        idealist = ideas.whereEqualTo("journeyID",jid).get().
        await().toObjects<Idea>() as ArrayList<Idea>
        idealist = ideas.whereEqualTo("categoryID",cid).get().
            await().toObjects<Idea>() as ArrayList<Idea>
        return withContext(Dispatchers.IO){ idealist } }


    fun addIdea (title: String, description: String){
        val idea = hashMapOf(
            "title" to title,
            "desc" to description,
            "journeyID" to jid,
            "categoryID" to cid
        )
        ideas.add(idea) }

    /*
        fun editIdea(categoryID: String, JourneyID: String, title: String, description: String){
            val idea = hashMapOf(
                "categoryID" to categoryID,
                "userID" to uid,
                "JourneyID" to JourneyID,
                "time" to Timestamp(System.currentTimeMillis()),
                "title" to title,
                "desc" to description
            )
            ideas.document(JourneyID).set(idea)
        }

        fun deleteIdea(ID: String){
            ideas.document(ID).delete()
        }


     */
}
