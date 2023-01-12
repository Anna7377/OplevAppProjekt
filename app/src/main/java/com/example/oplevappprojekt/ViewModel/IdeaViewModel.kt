package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.IdeaRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

data class IdeaState(
    val isCategorySelected: Boolean = false,
    val categoryID: String = " ",
   val  categoryName: String = " "
    //val currentIdea : idea? = null,
)

class IdeasViewModel{
    private val _uiState = mutableStateOf(IdeaState())
    val ideaRepo = IdeaRepository()
    val uiState: State<IdeaState> = _uiState
    val ideas = Firebase.firestore.collection("ideas")

    fun getCategorisedIdeas() : ArrayList<ideas>{
        var retideas: ArrayList<ideas>
        runBlocking{
            retideas = ideaRepo.getCategorisedIdeas(ID=uiState.value.categoryID)
        }
        System.out.println("CatID is: " + uiState.value.categoryID + " ideas are: " + retideas)
        return retideas
    }

    fun selectCat(ID: String, name: String){
        _uiState.value = _uiState.value.copy(isCategorySelected = true,
            categoryID = ID, categoryName = name)
    }

    fun deselect(){

    }

    fun deleteCategory(ID: String){
        runBlocking {
            ideaRepo.deleteCategory(ID) }
    }


    fun setIdea(journeyID: String?, categoryID: String?, title: String,
    desc: String, link: String){
        ideaRepo.setIdea(journeyID = journeyID, categoryID = categoryID, title = title,
        desc = desc, link=link)
    }
}
