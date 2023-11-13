package org.example.viewModel

import drafts.DraftPost
import org.uqbar.commons.model.annotations.Observable
import service.TiktokSystem

@Observable
class ControllerPost (private val system:TiktokSystem, var user: Controller){
    var id = ""
    var title = ""
    var likes = 0
    var comments = 0
    var description = ""
    var video = ""
    var editedTitle = title
        set(value){
            field = value
            validarPost()
        }
    var editedDescription = description
        set(value){
            field = value
            validarPost()
        }
    var editedVideo = video
        set(value){
            field = value
            validarPost()
        }
    var errorText = "\n\n\n"


    fun agregarPost(){
        val post = system.addPost(user.id, DraftPost(editedTitle,editedDescription,editedVideo))
        id = post.id
        title = editedTitle
        description = editedDescription
        video = editedVideo
        user.posteos.add(this)
    }

    fun editarPost(){
        system.editPost(id, user.id, DraftPost(editedTitle,editedDescription,editedVideo))
        title = editedTitle
        description = editedDescription
        video = editedVideo
    }

    fun accept(aTitle :String)
    {
        if(aTitle == "Edit post")
        {
            editarPost()
        }
        else
        {
            agregarPost()
        }
    }


    fun cancelar(){
        editedTitle = title
        editedDescription = description
        editedVideo = video
    }

    fun validarPost():Boolean
    {
        var esValido = true
        errorText = ""
        var cantidadDeSaltos = 3

        if(editedTitle == "")
        {
            errorText += "El titulo no puede estar vacio."
            esValido = false
            cantidadDeSaltos -= 1
        }
        if(editedDescription == "")
        {
            errorText += "\nLa descripcion no puede estar vacia. "
            esValido = false
            cantidadDeSaltos -= 1
        }
        if(editedVideo == "")
        {
            errorText += "\nLa url del video no puede estar vacia."
            esValido = false
            cantidadDeSaltos -= 1
        }
        while(cantidadDeSaltos != -1)
        {
            errorText += "\n"
            cantidadDeSaltos -= 1
        }
        return  esValido
    }
}
