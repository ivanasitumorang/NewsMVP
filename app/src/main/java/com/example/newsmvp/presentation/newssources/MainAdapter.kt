package com.example.newsmvp.presentation.newssources

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Source
import kotlinx.android.synthetic.main.item_source.view.*

class MainAdapter(val context: Context, var sourceList: List<Any>, val listener: ListenerNewsSource):  RecyclerView.Adapter<MainAdapter.NewsSourceViewHolder>() {
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_source, null)
        return NewsSourceViewHolder(view)
    }

    override fun getItemCount(): Int = sourceList.size

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        holder.bind(sourceList[position] as Source, listener)
    }

    fun addData(list: List<Any>){
        sourceList = list
        notifyDataSetChanged()
    }

    class NewsSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: Source, listener: ListenerNewsSource) = with(itemView){
            tvSourceName.text = source.name
            tvSourceDescription.text = source.description
            itemSource.setOnClickListener { listener.onClickNewsSource(source) }
        }
    }

    interface ListenerNewsSource {
        fun onClickNewsSource(source: Source)
    }
}