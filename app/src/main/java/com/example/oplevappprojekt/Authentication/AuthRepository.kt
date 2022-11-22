package com.example.oplevappprojekt.Authentication


/*
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    val currentUser = Firebase.auth.currentUser

    fun isloggedIn(): Boolean {
        if (currentUser == null) {
            return false
        } else {
            return true
        }
    }

    fun getUserId(): String {
        return currentUser?.uid.orEmpty()
    }

    fun newUser(mail: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                // account creation successful, upate the UI and send an account verification email
            }
            .addOnFailureListener {
                // account creation failed, probably the account already exists or bad network connection
                it.printStackTrace()
            }
    }


    suspend fun createUser(mail: String, pass: String, onComplete: (Boolean) -> Unit) =
        withContext(Dispatchers.IO)
        {
            Firebase.auth
                .createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onComplete.invoke(true)
                    } else {
                        onComplete.invoke(false)
                    }
                }.await()
        }
}

 *///