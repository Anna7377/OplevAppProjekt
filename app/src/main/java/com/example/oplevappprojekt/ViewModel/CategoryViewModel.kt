package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.Categories
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

//s215718

data class category(
    val title: String =""
)

data class categoryState(
    val isCategorySelected: Boolean = false,
    val currentCategory:Categories? = null,
    val userCategories: ArrayList<Categories> = arrayListOf(Categories("","",0))
)

class CategoryViewModel {
    private val _uiState = mutableStateOf(categoryState())
    val uiState: State<categoryState> = _uiState
    val categories = Firebase.firestore.collection("Categories")
    val idea = Firebase.firestore.collection("ideas")
    val idealist: ArrayList<Idea> = arrayListOf()

    fun getCategories() {
        categories.get().addOnSuccessListener { documents ->
            for (document in documents) {
                if (document.get("userID")?.equals(Firebase.auth.currentUser?.uid) == true) {
                }
            }
        }

        fun addCategories(title: String) {
            val categories = hashMapOf(
                "category" to title,
                "userID" to Firebase.auth.currentUser?.uid.toString()
            )

        }


        fun getIdeas() {
            idea.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document.get("Document ID")
                            ?.equals(Firebase.auth.currentUser?.uid) == true
                    ) {
                        idealist.add(document.toObject())
                    }
                }
            }
        }

        fun getIdeaslist(): ArrayList<Idea> {
            return idealist
        }
    }
}