package com.example.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.application.databinding.ActivityNewsRecyclerBinding


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsArray = ArrayList<News>()

    class NewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private var  binding = ActivityNewsRecyclerBinding.bind(item)
        fun setUp(news: News) = with(binding) {
            recycleNewsTitle.text = news.title
            recycleNewsDescription.text = news.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_news_recycler, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsArray.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setUp(newsArray[position])
    }

    fun clearNews(){
        newsArray.clear()
    }

    fun addNews(news: News){
        val position = newsArray.size // Получаем индекс для новой записи
        newsArray.add(news)
        notifyItemInserted(position) // Обновляем RecyclerView
    }

}