package com.example.oplevappprojekt.viewModel

import com.example.oplevappprojekt.data.MyUserProfileRepository
import kotlinx.coroutines.runBlocking

data class user(

    val name: String = " ",
    val userID: String = " ",
    val mail: String = " ",
    val verified: String = ""
)
class MyUserProfileViewModel {
    val userrepo = MyUserProfileRepository()

    fun readName(): String{
        var name = ""
        runBlocking {
            name = userrepo.readName()
        }
        return name

        println(name)
    }

    fun readMail(): String{
        var mail = ""
        runBlocking {
            mail = userrepo.readMail()
        }
        return mail
    }

    fun save(name: String, mail: String) {
        runBlocking {
            userrepo.save(name=name,mail=mail)
        }
    }

}