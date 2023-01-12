package com.example.oplevappprojekt.data
import com.example.oplevappprojekt.ViewModel.Category
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

//s215718

class Categories {
    val categories = Firebase.firestore.collection("categories")
    val jid = Firebase.auth.currentUser?.uid.toString()
    var categorylist: ArrayList<Category> = arrayListOf()

    suspend fun getCategories(): ArrayList<Category>{
        categorylist = categories.whereEqualTo("journeyID",jid).get().
        await().toObjects<Category>() as ArrayList<Category>
        return withContext(Dispatchers.IO){categorylist}
    }


    fun addCategory(title:String){
        val category = hashMapOf(
            "title" to title,
            "journeyID" to jid
        )
        categories.add(category)
    }
}