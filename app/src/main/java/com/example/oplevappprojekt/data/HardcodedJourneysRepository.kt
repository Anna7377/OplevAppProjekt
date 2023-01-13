package com.example.oplevappprojekt.data

import androidx.compose.ui.graphics.ImageBitmap
import com.example.oplevappprojekt.ViewModel.Journey
import com.example.oplevappprojekt.ViewModel.ideas
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.sql.Timestamp

//s215722

data class img(
   val img: ImageBitmap,
   val journeyID: String = ""
)

class HardcodedJourneysRepository {
    val uid = Firebase.auth.currentUser?.uid.toString()
    val journeys = Firebase.firestore.collection("journeys")
    var journeylist: ArrayList<Journey> = arrayListOf()
    val IDs : ArrayList<String> = arrayListOf()

    val category_collection = Firebase.firestore.collection("categories")
    var categorylist: ArrayList<category> = arrayListOf()

   suspend fun getJourneys(): ArrayList<Journey> {
       val coljourneylist: ArrayList<Journey>
       val colIDs: ArrayList<String> = arrayListOf()
       val unpinID: ArrayList<String> = arrayListOf()
// get pinned journeys
       val journeydocs = journeys.whereEqualTo("userID", uid).whereEqualTo("isPinned", true).get()
           .await()
       journeylist = journeydocs.toObjects<Journey>() as ArrayList<Journey>
       for (i in 0..journeydocs.size() - 1) {
           IDs.add(journeydocs.documents.get(i).id)
           journeylist.get(i).JourneyID = IDs.get(i)
       }
// get non-pinned journeys
       val journeydocs2 = journeys.whereEqualTo("userID", uid).whereEqualTo("isPinned", false).get()
           .await()
       val journeylist2 = journeydocs2.toObjects<Journey>() as ArrayList<Journey>
       for (i in 0..journeylist2.size - 1) {
           unpinID.add(journeydocs2.documents.get(i).id)
           journeylist2.get(i).JourneyID = unpinID.get(i)
           journeylist.add(journeylist2.get(i))
       }
// get collaborated journeys
       val coljourneys = Firebase.firestore.collection("users")
           .document(uid).collection("coljourneys")
       val coldocs = coljourneys.get().await()
       coljourneylist = coldocs.toObjects<Journey>() as ArrayList<Journey>
       for (i in 0..coldocs.size() - 1) {
           val journ = coljourneylist.get(i).originalJourneyID
           colIDs.add(coldocs.documents.get(i).id)
           if (journeys.document(journ).get().await().exists()) {
               coljourneylist.get(i).JourneyID = colIDs.get(i)
               journeylist.add(coljourneylist.get(i))
           } else {
               coljourneys.document(colIDs.get(i)).delete()
               colIDs.removeAt(i)
           }
       }
           return withContext(Dispatchers.IO) { journeylist }
   }

       suspend fun isCollaborated(ID: String): Boolean {
           val db = Firebase.firestore.collection("users")
               .document(uid).collection("coljourneys").document(ID).get().await()
           var iscol = false
           if (db.exists()) {
               iscol = true
           }
           return iscol
       }


       fun addJourney(country: String, date: String) {
           val journey = hashMapOf(
               "country" to country,
               "userID" to uid,
               "date" to date,
               "time" to Timestamp(System.currentTimeMillis()),
               "isPinned" to false
           )
           journeys.document().set(journey)
       }

       fun editJourney(journeyID: String, date: String, country: String) {
           val journey = hashMapOf(
               "country" to country,
               "userID" to uid,
               "date" to date,
               "time" to Timestamp(System.currentTimeMillis())
           )
           journeys.document(journeyID).update("country", country,
               "date", date)
       }

       fun deleteJourney(ID: String) {
           journeys.document(ID).delete()
       }

       suspend fun getImage(ID: String): ImageBitmap {
           val journ = Firebase.firestore
               .collection("journeyimages").document(ID)
               .get().await().toObject<img>()
           return journ?.img!!
       }

       suspend fun setImage(ID: String, img: ImageBitmap) {
           Firebase.firestore.collection("journeyimages").document(ID)
               .update("img", img)
       }

       fun pin(ID: String) {
           val doc = Firebase.firestore
               .collection("journeys").document(ID)
               .update("isPinned", true)

       }

    suspend fun getCategories(ID: String): ArrayList<category> {
        System.out.println(ID)
        val catdocs = category_collection.whereEqualTo("journeyID", ID)
            .get().await()
        val categories = catdocs.toObjects<category>()
        for(i in 0..catdocs.size()-1){
            categories.get(i).categoryID=catdocs.documents.get(i).id
        }
        categorylist = categories as ArrayList<category>
        System.out.println(categorylist)
        return categorylist
    }


    fun editCategory(name: String, ID: String){
        Firebase.firestore.collection("categories").document(ID).update("name", name)
    }

    suspend fun getOtherIdeas(ID: String) : ArrayList<ideas>{
        val ideas = Firebase.firestore.collection("ideas")
        val idealist = ideas.whereEqualTo("journeyID", ID).get().await().toObjects<ideas>()
    return idealist as ArrayList<ideas>
    }
   }

