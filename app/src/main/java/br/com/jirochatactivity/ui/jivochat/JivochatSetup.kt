package br.com.jirochatactivity.ui.jivochat

import java.io.Serializable

open class JivochatSetup : Serializable{
    open var language: String = "pt"

    open fun onEvent(name: String?, data: String?){}
}