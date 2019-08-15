package br.com.jirochatactivity.ui.jivochat

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jirochatactivity.R
import br.com.jirochatactivity.utils.Constants
import br.com.jivosite.sdk.JivoDelegate
import br.com.jivosite.sdk.JivoSdk
import kotlinx.android.synthetic.main.activity_jivochat.*
import org.jetbrains.anko.intentFor

class JivochatActivity : AppCompatActivity(), JivoDelegate{
//    private val jivoSdk: JivoSdk by lazy {
//        val sdk = JivoSdk(jivoChatWebView, "pt")
//        sdk.delegate = this
//        sdk
//    }

    private lateinit var jivoSdk: JivoSdk

    private lateinit var setup: JivochatSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jivochat)
        getExtras()
        jivoSdk.prepare()
    }

    override fun onEvent(name: String?, data: String?) {
        setup.onEvent(name, data)
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

    private fun getExtras() {
        setup = intent.getSerializableExtra(Constants.EXTRA_JIVOCHAT_SETUP) as JivochatSetup
        jivoSdk = JivoSdk(jivoChatWebView, setup.language)
        jivoSdk.delegate = this
    }
}

fun Context.createJivochatActivityIntent(jivochatSetup: JivochatSetup? = null) =
    intentFor<JivochatActivity>(
        Constants.EXTRA_JIVOCHAT_SETUP to jivochatSetup
    )