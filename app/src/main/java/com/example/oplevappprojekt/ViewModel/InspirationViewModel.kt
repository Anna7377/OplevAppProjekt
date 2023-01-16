package com.example.oplevappprojekt.ViewModel

import androidx.lifecycle.ViewModel
import com.example.oplevappprojekt.data.InspirationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking

class InspirationViewModel : ViewModel() {
    val insprepo = InspirationRepository()
    fun read() : String{
        var ret = ""
        runBlocking {
        ret = insprepo.read() }
        return ret
    }

    fun update(insp: String){
        insprepo.update(insp=insp, ID = Firebase.auth.currentUser?.uid.toString())
    }
}