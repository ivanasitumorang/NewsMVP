package com.example.newsmvp.presentation.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsmvp.R
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.toolbar_webview.*

class WebViewActivity : AppCompatActivity(){


    companion object {
        const val ARG_LINK = "ARTICLE_LINK"
        const val ARG_TITLE = "ARTICLE_TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val bundle = intent.extras
        if (bundle!=null){
            setContent(bundle.getString(ARG_LINK, ARG_TITLE))
            setupToolbar(bundle.getString(ARG_TITLE, getString(R.string.web_view_title)))
        }
    }

    fun setupToolbar(title:String?){
        tvToolbarTitle.text = title ?: getString(R.string.web_view_title)
        btnToolbarClose.setOnClickListener { onBackPressed() }
    }

    fun setContent(link: String){
        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.clearCache(true)
        webview.clearHistory()
        webview.loadUrl(link)
        webview.isHorizontalScrollBarEnabled = true
    }
}
