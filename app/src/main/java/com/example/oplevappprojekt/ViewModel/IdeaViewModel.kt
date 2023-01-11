package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.IdeaRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class IdeaState(
    val isIdeaSelected: Boolean = false,
    val currentIdeaID: String = " ",
    val categoryID: String = " "
    //val currentIdea : idea? = null,
)

class IdeasViewModel{
    private val _uiState = mutableStateOf(IdeaState())
    val ideaRepo = IdeaRepository()
    val uiState: State<IdeaState> = _uiState
    val ideas = Firebase.firestore.collection("ideas")

    suspend fun getCategorisedIdeas(ID: String) : ArrayList<ideas>{
     val  retideas = ideas.whereEqualTo("categoryID", ID)
         .get().await().toObjects<ideas>()
       return retideas as ArrayList<ideas>
    }

    fun setIdea(journeyID: String?, categoryID: String?, title: String,
    desc: String, link: String){
        ideaRepo.setIdea(journeyID = journeyID, categoryID = categoryID, title = title,
        desc = desc, link=link)
    }
}
