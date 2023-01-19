package com.example.oplevappprojekt.data

import android.annotation.SuppressLint
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

//s215722, s215726 & s216237

data class img(
   val img: ImageBitmap,
   val journeyID: String = ""
)

class HardcodedJourneysRepository {
    val uid = Firebase.auth.currentUser?.uid.toString()
    val journeys = Firebase.firestore.collection("journeys")
    val IDs : ArrayList<String> = arrayListOf()
    val category_collection = Firebase.firestore.collection("categories")
    var categorylist: ArrayList<category> = arrayListOf()

   suspend fun getJourneys(): ArrayList<Journey> {
       val journeylist: ArrayList<Journey> = arrayListOf()
       val coljourneylist: ArrayList<Journey>
       val colIDs: ArrayList<String> = arrayListOf()
       val pinColID: ArrayList<String> = arrayListOf()
       val unpinID: ArrayList<String> = arrayListOf()
// get pinned journeys
       val journeydocs = journeys.whereEqualTo("userID", uid).whereEqualTo("isPinned", true).get()
           .await()

       val journeyss = journeydocs.toObjects<Journey>() as ArrayList<Journey>

       for (journey in journeyss) {
           journeylist.add(journey)
       }

       for (i in 0..journeydocs.size() - 1) {
           IDs.add(journeydocs.documents.get(i).id)
           journeylist.get(i).JourneyID = IDs.get(i)
       }
       // get pinned coljourneys
       val collection = Firebase.firestore.collection("users")
           .document(uid).collection("coljourneys")
       val pincoldocs = collection.whereEqualTo("isPinned", true)
           .get().await()
       val pincoljourneys = pincoldocs.toObjects<Journey>() as ArrayList
       for (i in 0..pincoldocs.size() - 1) {
           val journ = pincoljourneys.get(i).originalJourneyID
           pinColID.add(pincoldocs.documents.get(i).id)
           if (journeys.document(journ).get().await().exists()) {
              pincoljourneys.get(i).JourneyID = pinColID.get(i)
               journeylist.add(pincoljourneys.get(i))
           } else {
              collection.document(colIDs.get(i)).delete()
               pinColID.removeAt(i)
           }
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
       val coldocs = coljourneys.whereNotEqualTo("isPinned", true).get().await()
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
           println("user ID: " + uid)
           journeys.document().set(journey)
       }

      @SuppressLint("SuspiciousIndentation")
      suspend fun editJourney(journeyID: String, date: String, country: String, isPinned: Boolean,
                              unPinned: Boolean, isOwned: Boolean) {
          var ref = journeys
          if(!isOwned){
              ref = Firebase.firestore.collection("users")
                  .document(uid).collection("coljourneys")
          }
          ref.document(journeyID).update("country", country,
              "date", date, "isPinned", isPinned, "unPinned", unPinned)
          if(isOwned){
              val colref = Firebase.firestore.collection("users")
                  .document(uid).collection("coljourneys")
                  .whereEqualTo("originalJourneyID", journeyID).get().await()
              val colrefedit = Firebase.firestore.collection("users")
                  .document(uid).collection("coljourneys")
              for(i in 0..colref.size()-1){
                  val ID = colref.documents.get(i).id
                  colrefedit.document(ID).update("country", country,
                  "date", date, "isPinned", isPinned)
              }
          }

       }

    suspend fun deleteCategory(ID: String){
        Firebase.firestore.collection("categories").document(ID).delete()
        val ideas = Firebase.firestore.collection("ideas").whereEqualTo("categoryID", ID).get().await()
        for(i in 0..ideas.size()-1){
            val ID = ideas.documents.get(i).id
            Firebase.firestore.collection("ideas").document(ID).delete()
        }
    }
      suspend fun deleteJourney(ID: String) {
           journeys.document(ID).delete()
       val journeycats = Firebase.firestore.collection("categories")
              .whereEqualTo("journeyID", ID).get().await()
          for(i in 0..journeycats.size()-1) {
              val catID = journeycats.documents.get(i).id
              deleteCategory(catID)
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

    suspend fun deleteOtherIdea(ID: String){
        val doc = Firebase.firestore.collection("ideas").document(ID).get()
            .await()
        if (doc.exists()) {
            Firebase.firestore.collection("ideas").document(ID).delete()
        }
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

