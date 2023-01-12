package com.example.oplevappprojekt.data
import com.example.oplevappprojekt.ViewModel.Category
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


//s215718 & s213370

class Categories {
    val categories = Firebase.firestore.collection("categories")
    var jid = Firebase.auth.currentUser?.uid.toString()
    var categorylist: ArrayList<Category> = arrayListOf()
    val cid = Firebase.auth.currentUser?.uid.toString()

    suspend fun getCategories(): ArrayList<Category>{
        categorylist = ArrayList<Category>()
            categories.whereEqualTo("journeyID",jid).get().
        await().forEach{
            val cat = Category(
                categoryID = it.id,
                journeyID = jid,
                title = it.data.get("title") as String?
            )
            categorylist.add(cat)
        }
        //toObjects<Category>() as ArrayList<Category>
        return withContext(Dispatchers.IO){categorylist}
    }


    fun addCategory(title:String){
        val category = hashMapOf(
            "title" to title,
            "journeyID" to jid
        )
        categories.add(category)
    }
    fun editCategory(categoryID: String, title: String){
        val category = hashMapOf(
            "title" to title,
            "journeyID" to jid
        )
        categories.document(categoryID).set(category)
    }
    fun deleteCategory(ID: String){
        categories.document(ID).delete()
    }
}



