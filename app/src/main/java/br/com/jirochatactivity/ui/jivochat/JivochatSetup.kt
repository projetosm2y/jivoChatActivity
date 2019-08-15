package br.com.jirochatactivity.ui.jivochat

import java.io.Serializable

open class JivochatSetup : Serializable{
    /**
    * Language abbreviation of chat
    * */
    open var language: String = "pt"

    /**
    * */
    open fun onEvent(name: String?, data: String?){}
}