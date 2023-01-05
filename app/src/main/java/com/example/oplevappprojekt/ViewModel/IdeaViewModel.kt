package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import com.example.oplevappprojekt.data.IdeaRepository
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

//s213370
data class Idea(
    val categoryID: String = "",
    val description: String = "",
    var JourneyID: String = "",
    val title: String = ""
)

data class ideaState(
    var isIdeaSelected: Boolean = false,
    var currentcategoryID: String? = null,
    var currentdescription: String? = null,
    var currentJourneyID: String? = null,
    var currenttitle: String? = null,
    var userideas: ArrayList<Idea> = arrayListOf())

class Ideaviewmodel {
    private val _uiState = mutableStateOf(ideaState())
    val uiState: State<ideaState> = _uiState
    val repo = IdeaRepository()

    fun getIdeas() {
        var ideas: ArrayList<Idea> = arrayListOf()
        runBlocking{
            ideas = repo.getIdeas()
        }
        _uiState.value = _uiState.value.copy(userideas = ideas)
    }

    fun addIdea(title: String, categoryID: String, JourneyID: String, description: String) {
        repo.addIdea(categoryID = categoryID, JourneyID = JourneyID, description = description, title = title)
    }

    fun selectIdea(categoryID: String, JourneyID: String, title: String) {
        _uiState.value = _uiState.value.copy(currentcategoryID = categoryID, currentJourneyID = JourneyID, currenttitle = title, isIdeaSelected = true)
    }
    fun deselect(){
        _uiState.value = _uiState.value.copy(isIdeaSelected = false)
    }

    fun editIdea(categoryID: String, JourneyID: String, title: String, description: String){
        repo.editIdea(categoryID = categoryID, JourneyID = JourneyID, title = title, description = description)
        _uiState.value = _uiState.value.copy(currentcategoryID = categoryID, currentJourneyID = JourneyID, currenttitle = title, isIdeaSelected = true)
    }

    fun deleteIdea(){
        repo.deleteIdea(uiState.value.currentcategoryID.toString())
        getIdeas()
    }



    fun randomImg(): Int {
        val i = ThreadLocalRandom.current().nextInt(0, 5)
        var img: Int = R.drawable.image6
        when (i) {
            1 -> img = R.drawable.image1
            2 -> img = R.drawable.image2
            3 -> img = R.drawable.image3
            4 -> img = R.drawable.image4
            5 -> img = R.drawable.image5
        }
        return img
    }
}