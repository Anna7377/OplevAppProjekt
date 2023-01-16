package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.ideas
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

//s215718

data class category(
    val name: String = "",
    val journeyID: String = "",
    var categoryID: String = ""
)

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

    suspend fun setcategory(name: String, ID: String) : String{
        var ret = "succesfully added"
        var flag = false
        val check = Firebase.firestore.collection("categories")
            .whereEqualTo("name", name).get().await().toObjects<category>()
        for(i in 0..check.size-1){
            if(check.get(i).name.equals(name)){
                System.out.println(name)
                System.out.println(check.get(i).name)
                ret = "already exists"
                flag = true
            }
        }
        if(!flag){
            val cat = hashMapOf(
                "name" to name,
                "journeyID" to ID
            )
            Firebase.firestore.collection("categories").document().set(cat)
        }
        return ret
    }

   suspend fun findCatID(name: String, ID: String) : String {
       var Id = ""
        val cat = Firebase.firestore.collection("categories")
            .whereEqualTo("name", name).whereEqualTo("journeyID", ID).get().await()
       if(cat.size()>0){
       for(i in 0..cat.size()-1){
           Id = cat.documents.get(i).id
       }
    }
       return Id
   }
    val ideas = Firebase.firestore.collection("ideas")

    suspend fun getCategorisedIdeas(ID: String) : ArrayList<ideas>{

        val ideas = ideas.whereEqualTo("categoryID", ID)
            .get().await()
        val  retideas = ideas.toObjects<ideas>()
    for(i in 0..ideas.size()-1){
        retideas.get(i).ID = ideas.documents.get(i).id
    }
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

    fun createIdea(title: String, desc: String, link: String, ID: String, journeyID: String, img: String){
       val idea = hashMapOf(
            "title" to title,
            "desc" to desc,
            "link" to link,
            "categoryID" to ID,
           "journeyID" to journeyID,
           "img" to img)

       Firebase.firestore.collection("ideas").document().set(idea) }


    private fun currentCollection(): CollectionReference =
        Firebase.firestore.collection(IDEA_COLLECTION)

    companion object {
        private const val IDEA_COLLECTION = "ideas"
    }

    suspend fun deleteIdea(ID:String) {
        val doc = Firebase.firestore.collection(IDEA_COLLECTION).document(ID).get()
            .await()
        if (doc.exists()) {
        Firebase.firestore.collection(IDEA_COLLECTION).document(ID).delete()
    }
    }



    }


