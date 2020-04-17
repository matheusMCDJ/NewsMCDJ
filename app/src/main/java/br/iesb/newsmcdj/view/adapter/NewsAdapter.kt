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
    RecyclerView.Adapter<NewsAdapter.NewsViewHolderCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolderCard {
        var view: View? = null
        if (viewType == 1) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.news_lista_lateral_item, parent, false)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.news_lista_lateral_item, parent, false)
        }

        var viewHolder: NewsViewHolderCard? = null

        if (viewType == 1) {
            viewHolder = NewsViewHolderCard(view)
        } else {
            viewHolder = NewsViewHolder(view)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            return 1
        } else {
            return 2
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolderCard, position: Int) {
        val news = dataSet[position]

        holder.title.text = news.title

        if (holder is NewsViewHolder) {
            holder.description.text = news.description
        }

        // Carrega a imagem de forma ASSÍNCRONA usando a biblioteca Picasso.
        // Essa biblioteca cria uma thread em background e retorna uma imagem
        // através de uma requisição HTTP.
        Picasso.get().load(news.image).into(holder.image);
    }

    open class NewsViewHolderCard(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.ivImage
        val title: TextView = itemView.tvTitle
    }

    class NewsViewHolder(itemView: View) : NewsViewHolderCard(itemView) {
        val description: TextView = itemView.tvDescription
    }

}