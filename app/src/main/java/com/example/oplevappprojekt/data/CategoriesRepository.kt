package com.example.oplevappprojekt.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject


//s215718 & s213370

data class Categories(
    val journeyID: String = "",
    val title: String = ""
)

interface CategoryRepo {
    suspend fun addCategory(category: com.example.oplevappprojekt.data.Categories)
    suspend fun getCategories(): List<com.example.oplevappprojekt.data.Categories>
}

class CategoriesRepository(private val firestore: FirebaseFirestore) : CategoryRepo {
    override suspend fun addCategory(category: Categories) {
        currentCollection().add(category)
    }

    val userCategories: ArrayList<com.example.oplevappprojekt.data.Categories> = arrayListOf()
    override suspend fun getCategories(): List<Categories> {
        currentCollection().get().addOnSuccesListener { documents ->
            for (document in documents) {
                if (document.get("Document ID")?.equals(Firebase.auth.currentUser?.uid) == true) {
                    userCategories.add(document.toObject())
                }
            }

        }
        return userCategories
    }

    private fun currentCollection(): CollectionReference = firestore.collection(CATEGORY_COLLECTION)

    companion object {
        private const val CATEGORY_COLLECTION = "categories"
    }

}

