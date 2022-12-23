package com.example.oplevappprojekt.Authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class userInfo {
    fun getinfo(){
       val currentUser = Firebase.auth.currentUser
    }
}