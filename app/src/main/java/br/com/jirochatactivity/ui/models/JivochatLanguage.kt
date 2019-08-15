package br.com.jirochatactivity.ui.models

enum class JivochatLanguage(val label: String) {
    PT("pt"),
    EN("agent.info")
}
/**
 * String extension that converts the string to
 * an accepted language
 * */
fun String.toJivochatLanguage(): JivochatLanguage? {
    return JivochatLanguage.values().find { it.label == this }
}