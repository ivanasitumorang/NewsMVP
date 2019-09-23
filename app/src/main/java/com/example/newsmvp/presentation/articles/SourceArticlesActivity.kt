package com.example.newsmvp.presentation.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import kotlinx.android.synthetic.main.activity_source_articles.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class SourceArticlesActivity : AppCompatActivity(), SourceArticlesContract.View {

    private lateinit var mPresenter: SourceArticlePresenter
    private lateinit var source: Source
    private lateinit var mAdapter: SourceArticleAdapter
    private lateinit var mActivityNavigation: ActivityNavigation

    override fun setupToolbar(sourceName: String) {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = getString(R.string.source_articles, sourceName)
        btnToolbarBack.setOnClickListener { onBackPressed() }
    }

    override fun setRecyclerView() {
        mAdapter = SourceArticleAdapter(this, arrayListOf(), object : SourceArticleAdapter.ListenerArticle{
            override fun onClickArticle(link: String, title: String) {
                mActivityNavigation.navigateToWebView(link, title)
            }

        })

        rvSourceArticleList.adapter = mAdapter
    }

    override fun initializeData(source: Source) {
        mPresenter.fetchArticlesBySource(source)
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
        val bundle = intent.extras
        if (bundle != null){
            source = bundle.getParcelable(ActivityNavigation.TAG_SOURCE)!!
        }
        mPresenter = SourceArticlePresenter()
        setNavigation()
        setupUI()
        setupToolbar(source.name)
        setRecyclerView()
        initializeData(source)
    }

}
