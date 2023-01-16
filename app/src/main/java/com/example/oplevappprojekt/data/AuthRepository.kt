package com.example.oplevappprojekt.data

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import com.example.oplevappprojekt.viewModel.Auth




class AuthRepository {
    val catrepo = HardcodedJourneysRepository()
    val profilerepo = MyUserProfileRepository()

    suspend fun SignUp(
        email: String,
        password: String,
        confPass: String,
        baseContext: Context,
        activity: Activity,
        name: String
    ): Boolean {
        var read = false
        // [START create_user_with_email]
        if (confPass != password) {
            Toast.makeText(baseContext, "Passwordsdonotmatch", Toast.LENGTH_SHORT).show()
        } else {

            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        read = true
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        //_uiState.value = _uiState.value.copy(mail = email)
                        //updateUI(true)
                        profilerepo.save(name=name, mail=email)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                       // updateUI(false)
                        runBlocking {
                            SignIn(email, password, baseContext, activity)

                        }

                       // val user = hashMapOf(
                          //  "mail" to email,
                          //  "username" to name)


                    }
                }.await()
        }
        // [END create_user_with_email]

        return read
    }

    suspend fun SignIn(email: String, password: String, baseContext: Context, activity: Activity):Boolean {
        // [START sign_in_with_email]
        var read = false
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    read = true
                    //_uiState.value = _uiState.value.copy(mail = email)
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    val user = Firebase.auth.currentUser
                    //updateUI(true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                   // updateUI(false)
                }
            }.await()
        // [END sign_in_with_email]
        return read
    }

   // fun updateUI(isSuccessful: Boolean) {
      //  _uiState.value = _uiState.value.copy(isLoggedIn = isSuccessful)}


    suspend fun deleteUser(ID: String) {
        Firebase.auth.currentUser?.delete()
        val journeycatidea = Firebase.firestore.collection("journeys")
            .whereEqualTo("userID", ID).get().await()
        for(i in 0..journeycatidea.size()-1) {
            val journeyID = journeycatidea.documents.get(i).id
            catrepo.deleteJourney(journeyID)
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        System.out.println("In Logout")

        //_uiState.value = _uiState.value.copy(isLoggedIn = false)
    }

    fun changePassword(currentPass: String, newPass: String, confirmNewPass: String) {
        val user = Firebase.auth.currentUser
        if (user != null) {
            if (newPass == confirmNewPass) {
                user!!.updatePassword(newPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(ContentValues.TAG, "Kodeordet er ændret")
                        }
                        else {
                            Log.e(ContentValues.TAG, "Kodeordet kunne ikke ændres: ${task.exception}")
                        }


                    }

            }


        }

    }
    fun emailVerification() {
        FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Verifikations e-mail er blevet sendt.")
                }
            }
    }

}
