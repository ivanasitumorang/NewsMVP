package com.example.newsmvp.presentation.common.navigationcontroller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.articles.SourceArticlesActivity
import com.example.newsmvp.presentation.webview.WebViewActivity

class ActivityNavigation internal constructor(val activity: AppCompatActivity){

    companion object {
        const val TAG_SOURCE_ID = "SOURCE_ID"
        const val TAG_SOURCE_NAME = "SOURCE_NAME"
    }

    /**
     * Navigate To Source's Articles
     * @param source
     **/
    fun navigateToSourceArticles(sourceId: String, sourceName: String){
        val sourceArticlesPage = newIntent(activity, SourceArticlesActivity::class.java)
        sourceArticlesPage.apply {
            // using parcelable extra
            putExtra(TAG_SOURCE_ID, sourceId)
            putExtra(TAG_SOURCE_NAME, sourceName)
        }
        activity.startActivity(sourceArticlesPage)
    }

    fun navigateToWebView(link: String, title:String){
        val webViewPage = newIntent(activity, WebViewActivity::class.java)
        webViewPage.apply {
            // using string extra
            putExtra(WebViewActivity.ARG_LINK, link)
            putExtra(WebViewActivity.ARG_TITLE, title)
        }
        activity.startActivity(webViewPage)
    }

    private fun<T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}