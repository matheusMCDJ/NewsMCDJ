package br.iesb.newsmcdj.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.view.adapter.NewsAdapter
import br.iesb.newsmcdj.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this). get(NewsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btProfile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        configureRecyclerView()
        showNews()
    }

    private fun configureRecyclerView() {
        rvNews.layoutManager = LinearLayoutManager(this)
    }

    private fun showNews() {
        viewModel.result.observe(this, Observer { news ->
            val adapter = NewsAdapter(news)
            rvNews.adapter = adapter
        })

        viewModel.topHeadLines()
    }

}

