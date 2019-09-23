package com.example.newsmvp.presentation.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.SearchView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.adapter.NewsAdapter
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import com.example.newsmvp.presentation.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_source_articles.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class SourceArticlesActivity : AppCompatActivity(), SourceArticlesContract.View {

    private lateinit var mPresenter: SourceArticlePresenter
    private lateinit var mAdapter: NewsAdapter
    private lateinit var mActivityNavigation: ActivityNavigation

    override fun setupToolbar(sourceName: String) {
        btnToolbarBack.visibility = VISIBLE
        searchView.visibility = VISIBLE
        tvToolbarTitle.text = getString(R.string.source_articles, sourceName)
        btnToolbarBack.setOnClickListener { onBackPressed() }
        searchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                tvToolbarTitle.visibility = VISIBLE
                btnToolbarBack.visibility = VISIBLE
                return false
            }
        })

        searchView.setOnSearchClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                tvToolbarTitle.visibility = GONE
                btnToolbarBack.visibility = GONE
            }
        })
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                mPresenter.searchArticlesByTitle(query?:"")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.searchArticlesByTitle(newText?:"")
                return true
            }
        } )

    }

    override fun setRecyclerView() {
        mAdapter = NewsAdapter(this, NewsAdapter.DATA_TYPE_ARTICLE, arrayListOf<Article>(), object : NewsAdapter.ListenerArticle{
            override fun onClickArticleItem(articleUrl: String, articleTitle: String?) {
                mActivityNavigation.navigateToWebView(articleUrl, articleTitle?:"Web View")
            }
        })

        rvSourceArticleList.adapter = mAdapter
    }

    override fun initializeData(sourceId: String) {
        mPresenter.fetchArticlesBySource(sourceId)
    }

    override fun setNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun setupUI() {
        mPresenter.setView(this)
    }

    override fun showProgressBar() {
        loadingIndicator.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        loadingIndicator.visibility = GONE
    }

    override fun setArticles(articles: List<Article>) {
        mAdapter.addData(articles)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_articles)
        var sourceId=""
        var sourceName=""
        val bundle = intent.extras
        if (bundle != null){
            sourceId = bundle.getString(ActivityNavigation.TAG_SOURCE_ID, "")
            sourceName = bundle.getString(ActivityNavigation.TAG_SOURCE_NAME, "Articles")
        }
        mPresenter = SourceArticlePresenter()
        setNavigation()
        setRecyclerView()
        setupUI()
        setupToolbar(sourceName)
        initializeData(sourceId)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
