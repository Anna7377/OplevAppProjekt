package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.Categories
import com.example.oplevappprojekt.model.Idea
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking

//s215718 & s213370

data class Category(
    val journeyID: String = "",
    val title: String = "",
    val img: String = "",
    val categoryID: String = "",
    var IdeaList: MutableList<com.example.oplevappprojekt.ViewModel.Idea> = mutableListOf<com.example.oplevappprojekt.ViewModel.Idea>()
)

data class categoryState(
    var isCategorySelected: Boolean = false,
    var currentCategory:Category? = null,
    var currentJourneyID: String? = null,
    var currentCategoryID: String? = null,
    var currenttitle: String? = null,
    var userCategories: ArrayList<Category> = arrayListOf(Category())
)

class CategoryViewModel {
    private val _uiState = mutableStateOf(categoryState())
    val uiState: State<categoryState> = _uiState
    val rep = Categories()
    var tmpTitle: String = ""



     fun getCategories() {
        var categories: ArrayList<Category> = arrayListOf()
        runBlocking {
            categories = rep.getCategories()
        }
         _uiState.value = _uiState.value.copy(userCategories = categories)
    }

    fun addCategory(title: String){
        rep.addCategory(title = title)
    }

    fun selectCategory(title: String,ID: String, il: String){
        _uiState.value = _uiState.value.copy(currenttitle = title,
            currentJourneyID = ID,
            currentCategoryID = il,
            isCategorySelected = true)
    }
}

