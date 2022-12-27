package com.example.oplevappprojekt.data

import com.example.oplevappprojekt.model.Idea


// s215718 & s213370
interface CategoryRepository {
    fun addCategory(category:Category)
    fun getCategories() : List<Categories>
    fun addIdea(idea: Idea)
    fun getIdea(): List<Idea>
}