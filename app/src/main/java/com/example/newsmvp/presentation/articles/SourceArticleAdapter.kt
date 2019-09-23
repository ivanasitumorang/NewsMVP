package com.example.newsmvp.presentation.articles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvp.R
import com.example.newsmvp.data.entities.Article
import kotlinx.android.synthetic.main.item_article.view.*

class SourceArticleAdapter (val context: Context, var articleList: List<Article>, val listener: ListenerArticle) : RecyclerView.Adapter<SourceArticleAdapter.SourceArticlesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceArticlesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, null)
        return SourceArticlesViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: SourceArticlesViewHolder, position: Int) {
        holder.bind(articleList[position], listener)
    }

    class SourceArticlesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(article: Article, listener: ListenerArticle) = with(itemView){
            tvArticleTitle.text = article.title
            tvArticleAuthor.text = article.author ?: "Undefined"
            tvArticleDescription.text = article.description
            itemArticle.setOnClickListener { listener.onClickArticle(article.url, article.title?:"Web View") }
        }
    }

    fun addData(articles: List<Article>){
        articleList = articles
        notifyDataSetChanged()
    }

    interface ListenerArticle {
        fun onClickArticle(link:String, title:String)
    }
}