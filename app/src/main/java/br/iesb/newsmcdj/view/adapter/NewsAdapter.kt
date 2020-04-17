package br.iesb.newsmcdj.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.domain.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_lista_lateral_item.view.*
import kotlinx.android.synthetic.main.news_lista_lateral_item.view.ivImage
import kotlinx.android.synthetic.main.news_lista_lateral_item.view.tvTitle
import kotlinx.android.synthetic.main.news_lista_lateral_item.view.*

class NewsAdapter(private val dataSet: Array<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_lista_lateral_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = dataSet[position]

        holder.title.text = news.title

        holder.description.text = news.description

        Picasso.get().load(news.image).into(holder.image);
    }

    open class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.ivImage
        val title: TextView = itemView.tvTitle
        val description: TextView = itemView.tvDescription
    }

}