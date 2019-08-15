package br.com.jirochatactivity.ui.jivochat

import java.io.Serializable

open class JivochatSetup : Serializable{
    var language: String = "pt"

    fun onEvent(name: String?, data: String?){}
}