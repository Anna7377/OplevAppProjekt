package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.ideas
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

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

    val ideas = Firebase.firestore.collection("ideas")

    suspend fun getCategorisedIdeas(ID: String) : ArrayList<ideas>{

        val ideas = ideas.whereEqualTo("categoryID", ID)
            .get().await()
        val  retideas = ideas.toObjects<ideas>()
        System.out.println("CategoryID is: " + ID + "Ideas are: " + retideas)
       /* for(i in 0..ideas.size()-1){
            retideas.get(i).categoryID=ideas.documents.get(i).id
        }

        */
        return retideas as ArrayList<ideas>
    }

    suspend fun deleteCategory(ID: String){
        Firebase.firestore.collection("categories").document(ID).delete()
        val ideas = Firebase.firestore.collection("ideas").whereEqualTo("categoryID", ID).get().await()
        for(i in 0..ideas.size()-1){
            val ID = ideas.documents.get(i).id
            Firebase.firestore.collection("ideas").document(ID).delete()
        }
    }

    fun createIdea(title: String, desc: String, link: String, ID: String, journeyID: String){
       val idea = hashMapOf(
            "title" to title,
            "desc" to desc,
            "link" to link,
            "categoryID" to ID,
           "journeyID" to journeyID)
       Firebase.firestore.collection("ideas").document().set(idea) }

    private fun currentCollection(): CollectionReference =
        Firebase.firestore.collection(IDEA_COLLECTION)

    companion object {
        private const val IDEA_COLLECTION = "Ideas"
    }
}

