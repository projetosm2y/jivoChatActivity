package br.com.jirochatactivity.ui.jivochat

import br.com.jirochatactivity.ui.models.JivochatEvent
import br.com.jirochatactivity.ui.models.JivochatLanguage
import java.io.Serializable

open class JivochatSetup : Serializable{
    /**
    * Language abbreviation of chat
    * */
    open var language: JivochatLanguage = JivochatLanguage.PT

    /**
     * This function is called when some events occur
     * in the chat web page
    * */
    open fun onEvent(name: JivochatEvent?, data: String?){}
}