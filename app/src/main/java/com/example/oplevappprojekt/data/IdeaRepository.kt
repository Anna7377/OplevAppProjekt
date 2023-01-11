package com.example.oplevappprojekt.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

//s215718



class IdeaRepository(){
   fun setIdea(title: String, desc: String, link: String,
               journeyID: String?, categoryID: String? ){
       val idea = hashMapOf(
           "title" to title,
           "desc" to desc,
           "link" to link,
           "journeyID" to journeyID,
           "categoryID" to categoryID
       )
       currentCollection().document().set(idea)
   }

    private fun currentCollection(): CollectionReference =
        Firebase.firestore.collection(IDEA_COLLECTION)

    companion object {
        private const val IDEA_COLLECTION = "Ideas"
    }
}

