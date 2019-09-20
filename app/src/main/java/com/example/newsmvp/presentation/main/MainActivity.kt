package com.example.newsmvp.presentation.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Source
import com.example.newsmvp.presentation.common.navigationcontroller.ActivityNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    override fun showProgressBar() {
        loadingIndicator.visibility = VISIBLE
    }

    override fun hideProgressBar() {
        loadingIndicator.visibility = GONE
    }

    lateinit var mPresenter : MainPresenter
    lateinit var mAdapter: MainAdapter
    lateinit var mActivityNavigation: ActivityNavigation

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

        mAdapter = MainAdapter(this, arrayListOf(), object : MainAdapter.ListenerNewsSource {
            override fun onClickNewsSource(source: Source) {
                mActivityNavigation.navigateToSourceArticles(source)
//                Toast.makeText(this@MainActivity, "${source.name} is clicked!", Toast.LENGTH_LONG).show()
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
    }

    override fun initializeData() {
        mPresenter.fetchNewsSources("en", "us")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter()
        setupUI()
        setNavigation()
        setRecyclerView()
        initializeData()
    }

}
