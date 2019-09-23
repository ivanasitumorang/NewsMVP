package com.example.newsmvp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Article
import com.example.newsmvp.data.entities.Source
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_source.view.*

class NewsAdapter (val context: Context, val dataType: String, var data: List<Any>, val listener: Any): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    companion object {
        const val DATA_TYPE_ARTICLE = "TYPE_ARTICLE"
        const val DATA_TYPE_SOURCE = "TYPE_SOURCE"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        if (dataType.equals(DATA_TYPE_ARTICLE)){
            return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, null))
        } else return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_source, null))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Any, listener: Any) = with(itemView){
            if (item is Article){
                listener as ListenerArticle
                tvArticleTitle.text = item.title
                tvArticleAuthor.text = item.author ?: "Undefined"
                tvArticleDescription.text = item.description
                itemArticle.setOnClickListener { listener.onClickArticleItem(item.url, item.title) }
            } else {
                item as Source
                listener as ListenerSource
                tvSourceName.text = item.name
                tvSourceDescription.text = item.description
                itemSource.setOnClickListener { listener.onClickSourceItem(item.id!!, item.name) }
            }
        }
    }

    fun addData(dataList: List<Any>){
        data = dataList
        notifyDataSetChanged()
    }

    interface ListenerSource {
        fun onClickSourceItem(sourceId:String, sourceTitle:String)
    }

    interface ListenerArticle {
        fun onClickArticleItem(articleUrl:String, articleTitle:String?)
    }
}