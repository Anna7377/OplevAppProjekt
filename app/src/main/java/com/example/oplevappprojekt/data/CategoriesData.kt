package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.ViewModel.category
import com.example.oplevappprojekt.model.Categories
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//s215718
class CategoriesData {
    val uid = Firebase.auth.currentUser?.uid.toString()
    val categories = Firebase.firestore.collection("categories")


}




//var category = mutableListOf<Categories>()

