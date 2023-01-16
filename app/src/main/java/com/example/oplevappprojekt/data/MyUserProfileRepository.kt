package com.example.oplevappprojekt.data
import android.util.Log
import com.example.oplevappprojekt.viewModel.user
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext



class MyUserProfileRepository {

    suspend fun readName(): String {

        val uid = Firebase.auth.currentUser?.uid.toString()
        val text = UserCollection().document(uid).get().await()
            .toObject<user>()

        Log.i("main",uid)
        return withContext(Dispatchers.IO) {
            text?.name.toString()
        }
    }

    suspend fun readMail(): String{

        val uid = Firebase.auth.currentUser?.uid.toString()
        val text = UserCollection().document(uid).get().await()
            .toObject<user>()
        System.out.println(text?.mail.toString())

        return withContext(Dispatchers.IO) {
            text?.mail.toString()
        }
    }

    suspend fun userVerified(): String{
        val uid = Firebase.auth.currentUser?.uid.toString()
        val text = UserCollection().document(uid).get().await()
            .toObject<user>()
        if (Firebase.auth.currentUser?.isEmailVerified == true){
            System.out.println(text?.verified.toString())

            return withContext(Dispatchers.IO) {
                "Email er verificeret"
            }
    }
        else {
            return withContext(Dispatchers.IO) {
                "Email er ikke verificeret"
            }
    }}

    fun save (name: String, mail: String) {
        val uid = Firebase.auth.currentUser?.uid.toString()
        val temp = hashMapOf(
            "name" to name,
            "mail" to mail,
            "userID" to uid)
        UserCollection().document(uid).set(temp)
    }
    private fun UserCollection(): CollectionReference =
        Firebase.firestore.collection(USERPROFILE_COLLECTION)

    companion object User {
        private const val USERPROFILE_COLLECTION = "users"

    }
}
