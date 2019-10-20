package com.example.newsmvp.presentation.common.navigationcontroller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.newsmvp.presentation.articles.SourceArticlesActivity
import com.example.newsmvp.presentation.newssources.NewsSourcesActivity.Companion.TAG_SOURCE_ID
import com.example.newsmvp.presentation.newssources.NewsSourcesActivity.Companion.TAG_SOURCE_NAME
import com.example.newsmvp.presentation.webview.WebViewActivity
import javax.inject.Inject

class ActivityNavigation @Inject constructor(private val activity: AppCompatActivity){

    /**
     * Navigate To Source's Articles
     * @param sourceId
     * @param sourceName
     **/
    fun navigateToSourceArticles(sourceId: String, sourceName: String){
        val sourceArticlesPage = newIntent(activity, SourceArticlesActivity::class.java)
        sourceArticlesPage.apply {
            putExtra(TAG_SOURCE_ID, sourceId)
            putExtra(TAG_SOURCE_NAME, sourceName)
        }
        activity.startActivity(sourceArticlesPage)
    }

    /**
     * Navigate To Web View
     * @param link
     * @param title
     **/
    fun navigateToWebView(link: String, title:String){
        val webViewPage = newIntent(activity, WebViewActivity::class.java)
        webViewPage.apply {
            putExtra(WebViewActivity.ARG_LINK, link)
            putExtra(WebViewActivity.ARG_TITLE, title)
        }
        activity.startActivity(webViewPage)
    }

    private fun<T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}