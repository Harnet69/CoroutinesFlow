package com.harnet.coroutinesflow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.harnet.coroutinesflow.R
import com.harnet.coroutinesflow.databinding.ItemArticleBinding
import com.harnet.coroutinesflow.model.Article

class ArticlesAdapter(private val articlesList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    fun updateArticlesList(newArticlesList: List<Article>) {
        articlesList.clear()
        articlesList.addAll(newArticlesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArticleBinding>(
            inflater,
            R.layout.item_article,
            parent,
            false
        )
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.view.article = articlesList[position]
    }

    class ArticlesViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    override fun getItemCount(): Int {
        return articlesList.size
    }
}