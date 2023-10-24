package org.example.vista

import org.example.viewModel.ControllerPost
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class ViewPost(parent: WindowOwner, controller: ControllerPost,private  val aTitle :String) : SimpleWindow<ControllerPost>(parent, controller) {


    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(p0: Panel?) {
        title = aTitle

        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Title:"
                width = 100
            }
            TextBox(it) with{
                bindTo("editedTitle")
                width = 200
            }
        }
        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Description:"
                width = 100
            }
            KeyWordTextArea(it) with{
                bindTo("editedDescription")
                width = 200
                height = 200
            }
        }
        Panel(p0) with {
            asHorizontal()
            Label(it) with {
                text = "Video:"
                width = 100
            }
            TextBox(it) with{
                bindTo("editedVideo")
                width = 200
            }
        }
        Label(p0) with {setForeground(Color.red)} withText "" bindTo ("errorText")

        Panel(p0) with {
            asHorizontal()
            Button(it) with{
                caption = "Accept"
                onClick {
                    if(thisWindow.modelObject.validarPost()) {
                        thisWindow.modelObject.accept(title)
                        close()
                    }

                }
                align = "center"
            }
            Button(it) with{
                caption = "Cancel"
                onClick {
                    thisWindow.modelObject.cancelar()
                    close()
                }
                align = "center"
            }
        }
    }
}