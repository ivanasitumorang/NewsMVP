package com.example.newsmvp.presentation.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import com.example.newsmvp.presentation.main.MainContract
import kotlinx.android.synthetic.main.activity_source_articles.*

class SourceArticlesActivity : AppCompatActivity(), SourceArticlesContract.View {

    private lateinit var mPresenter: SourceArticlePresenter
    private lateinit var source: Source
    private lateinit var mAdapter: SourceArticleAdapter

    override fun setRecyclerView() {
        mAdapter = SourceArticleAdapter(this, arrayListOf())

        rvSourceArticleList.adapter = mAdapter
    }

    override fun initializeData(source: Source) {
        mPresenter.fetchArticlesBySource(source)
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
        setupUI()
        setRecyclerView()
        initializeData(source)
    }
}
