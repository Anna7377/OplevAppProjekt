package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.Idea
import com.example.oplevappprojekt.ViewModel.Journey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.sql.Timestamp

// s213370

class IdeaRepository {

        val uid = Firebase.auth.currentUser?.uid.toString()
        val ideas = Firebase.firestore.collection("ideas")
        var idealist: ArrayList<Idea> = arrayListOf()
        val IDs : ArrayList<String> = arrayListOf()


        suspend fun getIdeas(): ArrayList<Idea> {

            /*  journeylist = journeys.whereEqualTo("userID", uid).get()
                 .await()
                 .toObjects<Journey>() as ArrayList<Journey>
             */
            val ideadocs = ideas.whereEqualTo("userID", uid).get()
                .await()
            idealist = ideadocs.toObjects<Idea>() as ArrayList<Idea>
            for(i in 0..ideadocs.size()-1) {
                IDs.add(ideadocs.documents.get(i).id)
                idealist.get(i).JourneyID=IDs.get(i)
                System.out.println(idealist.get(i).JourneyID)}

            return withContext(Dispatchers.IO){ idealist } }


        fun addIdea(categoryID: String, JourneyID: String){
            val idea = hashMapOf(
                "categoryID" to categoryID,
                "userID" to uid,
                "JourneyID" to JourneyID,
                "time" to Timestamp(System.currentTimeMillis())
            )
            ideas.document().set(idea) }

        fun editIdea(categoryID: String, JourneyID: String, title: String){
            val idea = hashMapOf(
                "categoryID" to categoryID,
                "userID" to uid,
                "JourneyID" to JourneyID,
                "time" to Timestamp(System.currentTimeMillis())
            )
            ideas.document(JourneyID).set(idea)
        }

        fun deleteIdea(ID: String){
            ideas.document(ID).delete()
        }

    }
