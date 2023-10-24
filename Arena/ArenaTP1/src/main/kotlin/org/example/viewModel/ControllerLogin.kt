package org.example.viewModel

import exceptions.UserException
import model.User
import org.uqbar.commons.model.annotations.Observable
import service.TiktokSystem


@Observable
class ControllerLogin(private val system: TiktokSystem) {
    var username  :String  = ""
        set(value){
            field = value
            errorText = ""
        }
    var password  :String  = ""
        set(value){
            field = value
            errorText = ""
        }
    var errorText :String? = ""


    fun system() : TiktokSystem
    {
        return system
    }

    fun  verificarLogeo() : User? {
        var usuario :User? = null

        try {
             usuario = system.login(username, password)
             errorText = ""
        }
        catch (e: UserException){
            errorText = "Usuario o contraseña incorrectos!!!"
        }

        return usuario
    }
}