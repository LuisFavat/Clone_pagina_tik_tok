package org.example.vista

import org.example.viewModel.Controller
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class ViewEditUser (parent: WindowOwner, controller: Controller): SimpleWindow<Controller>(parent,controller) {
    override fun addActions(p0: Panel?) {

    }


    override fun createFormPanel(panel: Panel?) {
        title = "Edit User"

        Label(panel) withText "Email: " with{
            TextBox(panel) bindTo ("emailEdited")
            width = 500
        }
        Label(panel) with {setForeground(Color.red)} withText "" bindTo "errorMail"

        Label(panel) withText "Para cambiar la contraseña rellena los 3 campos a continuacion "

        Label(panel) withText "Contraseña actual: "
        PasswordField(panel) bindTo ("currentPassword")

        Label(panel) withText "Contraseña nueva: "
        PasswordField(panel) bindTo ("passwordEdited1")

        Label(panel) withText "Repetir contraseña nueva"
        PasswordField(panel) bindTo ("passwordEdited2")

        Label(panel) with {setForeground(Color.red)} withText "" bindTo ("errorPassword")

        Label(panel) withText "Image: " with{
            TextBox(panel) bindTo ("imageEdited")
            width = 500
        }
        Label(panel) with {setForeground(Color.red)} withText "" bindTo ("errorText")


        Button(panel) with {
            caption = "Aceptar"
            onClick{
                if ( modelObject.validarEdicion()){
                    modelObject.editarUsuario()
                    close()
                }


            }
        }

        Button(panel) with {
            caption = "Cancelar"
            onClick{
                modelObject.volverEstadoOriginal()
                close()
            }
        }
    }
}
