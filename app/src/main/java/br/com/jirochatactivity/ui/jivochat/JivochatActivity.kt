package br.com.jirochatactivity.ui.jivochat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jirochatactivity.R
import br.com.jivosite.sdk.JivoDelegate
import br.com.jivosite.sdk.JivoSdk
import kotlinx.android.synthetic.main.activity_jivochat.*

class JivochatActivity : AppCompatActivity(), JivoDelegate{
    private val jivoSdk: JivoSdk by lazy {
        val sdk = JivoSdk(jivoChatWebView, "pt")
        sdk.delegate = this
        sdk
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jivochat)
        jivoSdk.prepare()
    }

    override fun onEvent(name: String?, data: String?) {
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
}