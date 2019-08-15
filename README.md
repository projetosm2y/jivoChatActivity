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
````
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
````

## Configuração do Jivochat
## Eventos disparados


