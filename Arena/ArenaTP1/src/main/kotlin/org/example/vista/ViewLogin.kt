package org.example.vista

import model.User
import org.example.viewModel.ControllerLogin
import org.example.viewModel.Controller

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color


class ViewLogin(parent: WindowOwner, controller: ControllerLogin) : SimpleWindow<ControllerLogin>(parent, controller) {


    override fun addActions(panel: Panel?) {

    }

    override fun createFormPanel(panel: Panel?) {

        title = "Login"

        Label(panel) withText "Username: " with{
            TextBox(panel) bindTo ("username")
            width = 400
        }

        Label(panel)  withText "Password: "
        PasswordField(panel) bindTo ("password")


        Label(panel) with {setForeground(Color.red)} withText "" bindTo ("errorText")

        Button(panel) with {
            caption = "Login"
            onClick{

                val user : User?
                user = modelObject.verificarLogeo()

                if(user != null){
                    close()
                    openVistaUsuario(thisWindow.owner, Controller(modelObject.system(), user))
                }
            }
        }
    }

    fun openVistaUsuario(parent: WindowOwner, controller: Controller)
    {
        ViewUser(parent, controller).open()
    }

}