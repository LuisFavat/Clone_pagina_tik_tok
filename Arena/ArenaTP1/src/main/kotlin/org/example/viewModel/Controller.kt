package org.example.viewModel

import drafts.DraftEditUser
import model.Post
import model.User
import org.uqbar.commons.model.annotations.Observable
import service.TiktokSystem

@Observable
class Controller (private val system:TiktokSystem, user:User){
    var id: String = user.id
    var username: String = user.username
    var email: String = user.email
    var selected: ControllerPost? = null
    var image : String = user.image

    var posteos = getPosts(user.posts)
    var emailEdited = user.email
        set(value){
            field = value
            userHelperForMailEditAndValidation()
        }
    var password = user.password
    var currentPassword = ""
        set(value){
            field = value
            userHelperForPasswordAndValidation()
        }
    var passwordEdited1 = ""
        set(value){
            field = value
            userHelperForPasswordAndValidation()
        }
    var passwordEdited2 = ""
        set(value){
            field = value
            userHelperForPasswordAndValidation()
        }
    var imageEdited: String = user.image
        set(value){
            field = value
            userHelperForImageEditAndValidation()
        }

    //Validaciones y ayudas al usuario
    var errorText = ""
    var errorPassword = "\n\n\n"
    var errorMail = ""

    var correctFormatMail = true
    var correctPassword   = true
    var correctImage      = true
    var passwordChange    = false


    fun system() : TiktokSystem
    {
        return system
    }

    fun editarUsuario(){
            val draftEditUser :DraftEditUser

            if(passwordChange)
            {
                draftEditUser = DraftEditUser(emailEdited,passwordEdited1, imageEdited)
                password = passwordEdited1
                passwordChange = false
            }
            else
            {
                draftEditUser = DraftEditUser(emailEdited,password, imageEdited)
            }

                system.editUser(id,draftEditUser)
                email = emailEdited
                currentPassword = ""
                passwordEdited1 = ""
                passwordEdited2 = ""
                image = imageEdited
        }

    fun volverEstadoOriginal(){
        emailEdited = email
        currentPassword = ""
        passwordEdited1 = ""
        passwordEdited2 = ""
        imageEdited = image
    }

    fun validarEdicion(): Boolean {
        var esValido = true

        errorText = ""

        if(seRepiteElMail())
        {
            errorText += "El mail ya se encuentra registrado. "
            esValido = false
        }
        else if(emailEdited == "")
        {

            esValido = false
        }
        esValido = esValido && correctFormatMail && correctPassword


        return esValido
    }

    fun userHelperForPasswordAndValidation()
    {
        correctPassword = true
        errorPassword = ""
        passwordChange = false


        if (currentPassword != "") {

            if (currentPassword == password) {

                if(passwordEdited1 == passwordEdited2 )
                {
                    if(passwordEdited1 !== "")
                    {
                        //al guardar la contraseña esta variable debe volver a false
                        passwordChange = true
                    }
                }
                else{
                    errorPassword += "La nueva contraseña no coincide.\n"
                    correctPassword = false
                }
                if (passwordEdited1 == "" ) {
                    errorPassword += "El campo contraseña nueva no puede estar vacio.\n"
                    correctPassword = false
                }
                if (passwordEdited2 == "") {
                    errorPassword += "El campo repetir contraseña nueva no puede estar vacio."
                    correctPassword = false
                }


            }
            else
            {
                errorPassword += "La contraseña actual no coincide."
                correctPassword = false
            }


        }
        fillStringWithNewLines(errorPassword)
    }

    fun userHelperForMailEditAndValidation()
    {
        correctFormatMail = true
        errorMail = ""

        if(!emailEdited.contains("@"))
        {
            errorMail += "Ingrese un email valido falta'@'."
            correctFormatMail = false
        }
        if(!emailEdited.contains(".com"))
        {
            errorMail += "Ingrese un email valido falta'.com'."
            correctFormatMail = false
        }
        if (emailEdited == "") {
            errorMail += "El mail no puede estar vacio. "
            correctFormatMail = false
        }
        if(correctFormatMail)
        {
            errorMail = ""
        }

    }

    fun userHelperForImageEditAndValidation()
    {
        correctImage = true
        errorText = ""

        if(imageEdited == "")
        {
            errorText = "La imagen no puede estar vacia"
            correctImage = false
        }
    }

    private fun seRepiteElMail(): Boolean
    {
        val usuarioConElMismoMail = system.users.filter{it.email == emailEdited}
        var seRepiteElMail = false
        if(usuarioConElMismoMail.isNotEmpty()) {
            if (usuarioConElMismoMail.first().id != id) {
                seRepiteElMail = true
            }
        }
        return  seRepiteElMail
    }

    fun getPosts(posts : List<Post>) : MutableList<ControllerPost> {
        posteos = posts.map {
            val p = ControllerPost(system, this)
            p.id = it.id
            p.title = it.title
            p.likes = it.likes.size
            p.comments = it.comments.size
            p.description = it.description
            p.video = it.video
            p.editedDescription = it.description
            p.editedVideo = it.video
            p.editedTitle = it.title
            p
        }.toMutableList()

        return posteos
    }

    fun fillStringWithNewLines(aStrig :String)
    {
        val newLines = aStrig.filter {  it == '\n'}.count()

        for (i in newLines..2) {
            errorPassword += "\n"
        }
    }
}
