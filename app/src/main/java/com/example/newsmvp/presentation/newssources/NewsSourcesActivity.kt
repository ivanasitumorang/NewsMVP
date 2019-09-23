package com.example.newsmvp.presentation.newssources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.adapter.NewsAdapter
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import kotlinx.android.synthetic.main.activity_news_sources.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class NewsSourcesActivity : AppCompatActivity(), NewsSourcesContract.View {

    lateinit var mPresenter : NewsSourcesPresenter
    lateinit var mAdapter: NewsAdapter
    lateinit var mActivityNavigation: ActivityNavigation

    override fun showProgressBar() {
        loadingIndicator.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        loadingIndicator.visibility = GONE
    }

    override fun setupToolbar() {
        tvToolbarTitle.text = "News Sources"
    }

    override fun setNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun setRecyclerView() {
        val layoutManager = object : LinearLayoutManager(this) {
            override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                return RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }

        mAdapter = NewsAdapter(this, NewsAdapter.DATA_TYPE_SOURCE, arrayListOf<Source>(), object : NewsAdapter.ListenerSource {
            override fun onClickSourceItem(sourceId: String, sourceName: String) {
                mActivityNavigation.navigateToSourceArticles(sourceId, sourceName)
            }

        })
        rvNewsSourceList.layoutManager = layoutManager
        rvNewsSourceList.adapter = mAdapter
    }

    override fun setNewsSources(sources: List<Source>) {
        mAdapter.addData(sources)
    }

    override fun setupUI() {
        mPresenter.setView(this)
        setupToolbar()
    }

    override fun initializeData() {
        mPresenter.fetchNewsSources("en", "us")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_sources)
        mPresenter = NewsSourcesPresenter()
        setupUI()
        setNavigation()
        setRecyclerView()
        initializeData()
    }

}
