package com.example.newsmvp.presentation.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsmvp.R
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.toolbar_webview.*

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val ARG_LINK = "ARTICLE_LINK"
        const val ARG_TITLE = "ARTICLE_TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val bundle = intent.extras
        if (bundle != null) {
            setContent(bundle.getString(ARG_LINK, ARG_TITLE))
            setupToolbar(bundle.getString(ARG_TITLE, getString(R.string.web_view_title)))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setupToolbar(title: String?) {
        tvToolbarTitle.text = title ?: getString(R.string.web_view_title)
        btnToolbarClose.setOnClickListener { onBackPressed() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setContent(link: String) {
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.clearCache(true)
        webView.clearHistory()
        webView.loadUrl(link)
        webView.isHorizontalScrollBarEnabled = true
    }
}
