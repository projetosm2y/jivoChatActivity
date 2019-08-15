package br.com.jirochatactivity.ui.jivochat

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jirochatactivity.R
import br.com.jirochatactivity.ui.models.toJivochatEvent
import br.com.jirochatactivity.utils.Constants
import br.com.jivosite.sdk.JivoDelegate
import br.com.jivosite.sdk.JivoSdk
import kotlinx.android.synthetic.main.activity_jivochat.*
import org.jetbrains.anko.intentFor

class JivochatActivity : AppCompatActivity(), JivoDelegate{
    private lateinit var jivoSdk: JivoSdk

    private val jivochatSetup: JivochatSetup by lazy {
        intent.getSerializableExtra(Constants.EXTRA_JIVOCHAT_SETUP) as JivochatSetup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jivochat)
        setup()
    }


    /**
     * It is called in some events that occur in the
     * chat web page.
     * */
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

    private fun setup() {
        jivoSdk = JivoSdk(jivoChatWebView, jivochatSetup.language.label)
        jivoSdk.delegate = this
        jivoSdk.prepare()
    }
}

fun Context.createJivochatActivityIntent(jivochatSetup: JivochatSetup = JivochatSetup()) =
    intentFor<JivochatActivity>(
        Constants.EXTRA_JIVOCHAT_SETUP to jivochatSetup
    )