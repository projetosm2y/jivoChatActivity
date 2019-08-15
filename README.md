# Mobile2You - Jivochat Activity
![Maven Central](https://img.shields.io/jitpack/v/github/projetosm2y/jivoChatActivity)

## Objetivo
Jivochat Activity é uma biblioteca que permite facilitar a instalação do sdk do Jivochat. Este é um serviço de chats, que disponibiliza um site facilmente configurável para cada projeto. Leia mais, no [site](https://www.jivochat.com.br) 
## Configuração da biblioteca

Para adicionar essa biblioteca como uma dependência num projeto online, precisa seguir os seguintes passos:

### 1. Autenticação do Jitpack
Para isso adicione o token para autenticação no arquvio **gradle.properties**
````
authToken=jp_2bfkj8gscb8bpi2jbdbnvltunn
````

No **build.gradle** no projeto adicione a seguinte declaraçnao
```kotlin
allprojects {
    repositories {
        ...
        maven {
            url "https://jitpack.io"
            credentials { username authToken }
        }
        ...
    }
}
```

### 2. Adicione a dependência no **build.gradle**

``` kotlin
dependencies {
    ...
    implementation 'com.github.projetosm2y:jivoChatActivity:<versão>'
    ...
}
```
A última versão disponível encontra-se na insígnia em cima desse documento. Já a última estável é a versão **0.0.2**

## Configuração do Jivochat

### 1. Configurações do SDK do Jivochat
O Jivochat exige que alguns arquivos, em um formato específico, estejam dentro da pasta *assets*. Baixe o *zip* do projeto, que se encontra no [github](https://github.com/JivoSite/MobileSdk) deles. 

Pegue a pasta *html* e a coloque dentro da pasta comentada, com todos os aquivos. Talvez haja a necessidade de criar a pasta *assets*, faça isso dentro do diretório *main* do seu projeto android.

No final você ficará com essa disposição:
````bash 
├── main
│   ├── java
│   ├── res
│   └── assets
│       └── html
│           ├── bundle.js
│           ├── index_en.html
│           └── index_ru.html
````

As configurações relativas a página do chat que será aberto, deverão ser definidas no arquivo **index_[idioma].html**. Esse idioma segue o padrão [ISO 639-1](https://pt.wikipedia.org/wiki/ISO_639#P), o mais comum para abreviação de idiomas. Além disso, há um *enum* na biblioteca para facilitar essa indentificação. 

```kotlin
enum class JivochatLanguage(val label: String) {
    PT("pt"),
    EN("en"),
    RU("ru")
}
```

Dentro de cada arquivo **index_.html** há um script com um objeto no formato padrão de objetos do Javascript (JSON), com os seguintes parâmetros:

```javascript
        jivo_config = {
            "plane_color":"yellow", //cor do botão de enivar

            "agentMessage_bg_color":"red", //cor do box da mensagem dos agentes
            "agentMessage_txt_color":"white", //cor texto da mensagem dos agentes
            "clientMessage_bg_color":"light_gray", //cor do box da mensagem dos clientes
            "clientMessage_txt_color":"black", //cor texto da mensagem dos clientes
            "active_message": "How can I help you?",
            "placeholder": "Send message",
            "app_link": "AppGás Chat", //link que aparece para o operador 

            "widget_id": "[disponível na plataforma admin do jivochat]", //widget_id
            "site_id": 0 , //site_id, também disponível na plataforma admin do jivochat
        }
```

Para mais informações sobre as coonfigurações do sdk do Jivochat, acesse o link do [README em inglês](https://github.com/JivoSite/MobileSdk/blob/master/README.EN.md) ou da [documentação oficial](docs.google.com/document/d/1Pl9O38eOCYzHQDB5CSat42JtSL_ESQKn_Y-4b6Hn5es/edit)

### 2. Configurações da biblioteca

Criamos uma classe para facilitar a configuração da biblioteca. Ela se chama *JivochatSetup*

``` kotlin
open class JivochatSetup : Serializable{
    /**
    * Idioma de configuração
    * */
    open var language: JivochatLanguage = JivochatLanguage.PT

    /**
     * Função que é chamada quando 
     * certos eventos são disparados
     * pelo chat
    * */
    open fun onEvent(name: JivochatEvent?, data: String?){}
}
```

Para se utilizar a activity, deve-se chamar a função que já cria a *intent* da 
*JivochatActivity* passando um objeto que sobrescreva as informações da classe. Veja abaixo:

``` kotlin
        val setup = object : JivochatSetup() {
            override var language: JivochatLanguage = JivochatLanguage.PT

            override fun onEvent(name: JivochatEvent?, data: String?){
                // tratamento para cada tipo de evento
            }
        }
```

As configurações defaults contam com idioma em português e uma função que não tem nenhuma linha de código. A própria *JivochatActivity* já trata um caso comum de eventos: abrir o navegador com uma *url* enviada nas mensagens do chat,como se pode ver abaixo:

```kotlin
    override fun onEvent(name: String?, data: String?) {
        jivochatSetup.onEvent(name?.toJivochatEvent(), data)
        name?.let {
            when(it) {
                "url.click" -> {
                    if(data != null ){
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data)))
                    }
                }
            }
        }
    }
```
## Eventos disparados

A lista de eventos que podem ser disparados pelo chat:

```kotlin
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
```

## Links importantes
1. [Site do Jivochat](https://www.jivochat.com.br)
2. [Github do Jivochat](https://github.com/JivoSite/MobileSdk) 
3. [README do Jivochar em inglgês](https://github.com/JivoSite/MobileSdk/blob/master/README.EN.md)
4. [Mais recente documentação oficial do Jivohcat](docs.google.com/document/d/1Pl9O38eOCYzHQDB5CSat42JtSL_ESQKn_Y-4b6Hn5es/edit)