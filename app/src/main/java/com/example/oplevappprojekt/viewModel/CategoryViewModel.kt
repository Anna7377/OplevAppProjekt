package com.example.oplevappprojekt.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.Categories
import kotlinx.coroutines.runBlocking

//s215718

data class Category(
    val journeyID: String = "",
    val title: String = "",
    val img: String = ""
)

data class categoryState(
    var isCategorySelected: Boolean = false,
    var currentCategory:Category? = null,
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

    fun selectCategory(title: String,img: Int){
        _uiState.value = _uiState.value.copy(currenttitle = title)
    }
}

