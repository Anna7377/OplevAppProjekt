package com.example.oplevappprojekt.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class category(
    val name: String = "",
    val journeyID: String = "",
var categoryID: String = ""
)
class backupRepoCat {
    val category_collection = Firebase.firestore.collection("categories")
    var categorylist: ArrayList<category> = arrayListOf()

    suspend fun getCategories(ID: String): ArrayList<category> {
        val catdocs = category_collection.whereEqualTo("journeyID", ID)
            .get().await()
        val categories = catdocs.toObjects<category>()
        for(i in 0..categories.size-1){
            categories.get(i).categoryID=catdocs.documents.get(i).id
        }
        categorylist = categories as ArrayList<category>
        return categorylist
    }

    fun setcategory(name: String, ID: String) {
        val cat = hashMapOf(
            "name" to name,
            "journeyID" to ID
        )
        category_collection.document().set(cat)
    }
}