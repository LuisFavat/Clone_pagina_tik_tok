package org.example.vista



import org.example.viewModel.ControllerPost
import org.example.viewModel.Controller
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class ViewUser (parent: WindowOwner, controller: Controller) : SimpleWindow<Controller>(parent, controller){
    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(p0: Panel) {
        title = "User"

        Panel(p0) with{
            asHorizontal()
            Label(it) with{
                text = "Id: "
                align = "left"
                width = 60
            }
            Label(it) with{
                bindTo("id")
                align = "left"
                width = 400
            }
        }

        Panel(p0) with{
            asHorizontal()
            Label(it) with{
                text = "Username: "
                align = "left"
                width = 60
            }
            Label(it) with{
                bindTo("username")
                align = "left"
            }
        }

        Panel(p0) with{
            asHorizontal()
            Label(it) with{
                text = "Email: "
                align = "left"
                width = 60
            }
            Label(it) with{
                bindTo("email")
                align = "left"
            }
        }
        Panel(p0) with{
            asHorizontal()
            Button(it) with{
                caption = "Edit user"
                onClick {
                    openViewEditUser(thisWindow.owner,thisWindow.modelObject)
                }
                align = "left"
            }
        }
        table<ControllerPost>(p0) {
            bindItemsTo("posteos")
            bindSelectionTo("selected")
            visibleRows = 10
            column {
                title = "Id"
                fixedSize = 30
                bindContentsTo("id")
            }
            column {
                title = "Tittle"
                weight = 50
                align("center")
                bindContentsTo("title")
            }
            column {
                title = "Likes"
                align("right")
                bindContentsTo("likes")
            }
            column {
                title = "Comments"
                align("right")
                bindContentsTo("comments")
            }
        }
        Panel(p0) with {
            asHorizontal()
            Button(it) with{
                caption = "Add new post"
                onClick {openVistaPost(thisWindow.owner, ControllerPost(thisWindow.modelObject.system(),thisWindow.modelObject), "Add new post" ) }
                align = "left"
            }
            Button(it) with{
                caption = "Edit post"
                onClick { thisWindow.modelObject.selected?.let { it1 -> openVistaPost(thisWindow.owner, it1, "Edit post" ) } }
                align = "left"
            }
        }
    }

    fun openVistaPost(parent: WindowOwner, controller: ControllerPost, title: String){
        ViewPost(parent, controller, title).open()
    }

    fun openViewEditUser (parent: WindowOwner, controller: Controller){
        ViewEditUser(parent,controller).open()
    }
}