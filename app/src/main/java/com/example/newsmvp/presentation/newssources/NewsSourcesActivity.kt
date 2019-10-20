package com.example.newsmvp.presentation.newssources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import com.example.newsmvp.NewsApp
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.external.AppSchedulerProvider
import com.example.newsmvp.external.SchedulerProvider
import com.example.newsmvp.presentation.adapter.NewsAdapter
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_news_sources.*
import kotlinx.android.synthetic.main.toolbar_activity.*
import javax.inject.Inject

class NewsSourcesActivity : AppCompatActivity(), NewsSourcesContract.View {

    @Inject lateinit var mPresenter: NewsSourcesPresenter
    private lateinit var mAdapter: NewsAdapter
    @Inject lateinit var mActivityNavigation: ActivityNavigation

    companion object {
        const val TAG_SOURCE_ID = "SOURCE_ID"
        const val TAG_SOURCE_NAME = "SOURCE_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_sources)
//        (application as NewsApp).appComponent.inject(this)

        mPresenter.setView(this)
        setupUI()
        initializeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.cancelFetchSources()
    }

    override fun showProgressBar() {
        loadingIndicator.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        loadingIndicator.visibility = GONE
    }

    override fun setupToolbar() {
        tvToolbarTitle.text = getString(R.string.news_sources)
    }

    override fun setNavigation() {
    }

    override fun setRecyclerView() {
        mAdapter = NewsAdapter(
            this,
            NewsAdapter.DATA_TYPE_SOURCE,
            arrayListOf<Source>(),
            object : NewsAdapter.ListenerSource {
                override fun onClickSourceItem(sourceId: String, sourceName: String) {
                    mActivityNavigation.navigateToSourceArticles(sourceId, sourceName)
                }
            })
        rvNewsSourceList.adapter = mAdapter
    }

    override fun setNewsSources(sources: List<Source>?) {
        mAdapter.setList(sources)
    }

    override fun setupUI() {
        setupToolbar()
        setNavigation()
        setRecyclerView()
    }

    override fun initializeData() {
        mPresenter.fetchNewsSources("en", "us")
    }

}
