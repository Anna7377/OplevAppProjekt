package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Idea(
    val title: String,
    val desc: String,
    val journeyID: String
)

data class IdeaState(
    val isIdeaSelected: Boolean = false,
    val currentIdea : Idea? = null,
)

class IdeasViewModel{
    private val _uiState = mutableStateOf(IdeaState())
    val uiState: State<IdeaState> = _uiState
    val ideas = Firebase.firestore.collection("ideas")

    fun getIdeas(){
        ideas.get().addOnSuccessListener { documents -> }
    }
}
