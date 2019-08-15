package br.com.jirochatactivity.ui.models

/**
 * Those are all the possibilities of events
 * that can be monitored by the JivoDelegate
 * */
enum class JivochatEvent(val label: String) {
    AGENT_CHAT_CLOSE("agent.chat_close"),
    AGENT_INFO("agent.info"),
    AGENT_MESSAGE("agent.message"),
    AGNET_NAME("agent.name"),
    CHAT_ACCEPT("chat.accept"),
    CHAT_FORCE_OFFLINE("chat.force_offline"),
    CHAT_MODE("chat.mode"),
    CHAT_READY("chat.ready"),
    CHAT_TRANSFERRED("chat.transferred"),
    CONNECTION_CONNECT("connection.connect"),
    CONNECTION_CONNECTING("connection.connecting"),
    CONNECTION_DISCONNECT("connection.disconnect"),
    CONNECTION_ERROR("connection.error"),
    CONTACT_INFO("contact_info"),
    URL_CLICK("url.click")
}

/**
 * String extension that converts the string to
 * a JivochatEvent
 * */
fun String.toJivochatEvent(): JivochatEvent? {
    return JivochatEvent.values().find { it.label == this }
}