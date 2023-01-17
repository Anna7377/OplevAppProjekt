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
   val  categoryName: String = " ",
    val addMessage: String = " ",
    val isIdeaSelected: Boolean = false,
    val ideaID: String = "",
    val ideadesc: String = "",
val ideatitle: String = "",
    val idealink: String = "",
    val ideaimg: String = "",
val idealist: ArrayList<ideas> = arrayListOf()
)

class IdeasViewModel{
    private val _uiState = mutableStateOf(IdeaState())
    val ideaRepo = IdeaRepository()
    val uiState: State<IdeaState> = _uiState
    val ideas = Firebase.firestore.collection("ideas")

    fun getCategorisedIdeas() {
        var retideas: ArrayList<ideas>
        runBlocking{
            retideas = ideaRepo.getCategorisedIdeas(ID=uiState.value.categoryID)
        }
        _uiState.value = _uiState.value.copy(idealist = retideas)
        //return retideas
    }

    fun selectCat(ID: String, name: String){
        _uiState.value = _uiState.value.copy(isCategorySelected = true,
            categoryID = ID, categoryName = name)
    }

    fun setcategory(title: String, ID: String) {
        var ret: String
        runBlocking { ret = ideaRepo.setcategory(name = title, ID = ID ) }
        _uiState.value = _uiState.value.copy(addMessage = ret)

    }
    fun deselect(){
_uiState.value = _uiState.value.copy(isCategorySelected = false, categoryName = "",
categoryID = "")
    }

    fun createIdea(title: String, desc: String, link: String, journeyID: String, img: String){
        runBlocking {
            ideaRepo.createIdea(title = title, desc=desc,
                link=link, ID=uiState.value.categoryID, journeyID = journeyID, img = img)
        }
    }

    fun getCatID(name: String, ID: String){
        var catID = " "
        runBlocking { catID = ideaRepo.findCatID(name = name, ID = ID) }
        _uiState.value = _uiState.value.copy(categoryID = catID)
    }

    fun deleteCategory(ID: String){
        runBlocking {
            ideaRepo.deleteCategory(ID)
        }
    }

    fun selectIdea(ideaID: String, desc: String, link: String,
                   img: String, title: String){
        _uiState.value = _uiState.value.copy(ideaID =ideaID,
            isIdeaSelected = true, ideadesc = desc, ideatitle = title,
            ideaimg = img, idealink = link
        ) }

    fun deleteIdea(ID: String){
        runBlocking {
            ideaRepo.deleteIdea(ID)
        }
        getCategorisedIdeas()
    }

    fun deselectIdea(){
        _uiState.value = _uiState.value.copy(isIdeaSelected = false,
        idealink = "", ideadesc = "", ideatitle = "", ideaimg = "")
    }

    fun editIdea(title: String, desc: String, link: String, img: String){
        ideaRepo.editIdea(title = title, desc = desc, link = link,
        img = img, ID = uiState.value.ideaID)
    }

}
