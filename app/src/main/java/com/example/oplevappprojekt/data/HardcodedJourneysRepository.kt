package com.example.oplevappprojekt.data

import androidx.compose.ui.graphics.ImageBitmap
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.Journey
import com.example.oplevappprojekt.ViewModel.ideas
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.util.concurrent.ThreadLocalRandom

//s215722

data class img(
   val img: ImageBitmap,
   val journeyID: String = ""
)

class HardcodedJourneysRepository {
    val idearepo = IdeaRepository()
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
               "isPinned" to false,
               "img" to randomImg()
           )
           journeys.document().set(journey)
       }

      suspend fun editJourney(journeyID: String, date: String, country: String, isPinned: Boolean, unPinned: Boolean) {
           journeys.document(journeyID).update("country", country,
               "date", date, "isPinned", isPinned, "unPinned", unPinned)
           val usersRef = Firebase.firestore.collection("users")
           val users = usersRef.get().await()
          var coljourneys: QuerySnapshot
           for(i in 0..users.size()-1){
               val uid = users.documents.get(i).id
               coljourneys = usersRef.document(uid).collection("coljourneys")
                   .whereEqualTo("originalJourneyID", journeyID).get().await()
               for(i in 0..coljourneys.size()-1){
                 val docid = coljourneys.documents.get(i).id
                   usersRef.document(uid).collection("coljourneys").document(docid)
                       .update("country", country, "date", date)
               }
           }
       }

      suspend fun deleteJourney(ID: String) {
           journeys.document(ID).delete()
       val journeycats = Firebase.firestore.collection("categories")
              .whereEqualTo("journeyID", ID).get().await()
          for(i in 0..journeycats.size()-1) {
              val catID = journeycats.documents.get(i).id
              idearepo.deleteCategory(catID)
          }
       }

       suspend fun getImage(ID: String): ImageBitmap {
           val journ = Firebase.firestore
               .collection("journeyimages").document(ID)
               .get().await().toObject<img>()
           return journ?.img!!
       }

       fun setImage(ID: String, img: ImageBitmap) {
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
        val docref = ideas.get().await()
        val idealist = ideas.whereEqualTo("journeyID", ID)
            .whereEqualTo("categoryID", "")
            .get()
            .await().toObjects<ideas>()
        for(i in 0..idealist.size-1){
            idealist.get(i).ID = docref.documents.get(i).id
        }
    return idealist as ArrayList<ideas>
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

