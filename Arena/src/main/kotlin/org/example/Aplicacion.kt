package org.example

import bootstrap.Bootstrap
import org.example.viewModel.ControllerLogin
import org.example.vista.ViewLogin
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class Aplicacion :Application() {

    val system =  Bootstrap().getSystem()

    override fun createMainWindow(): Window<*> {

        return ViewLogin(this, ControllerLogin(system))
    }
}

fun main()
{
    Aplicacion().start()
}