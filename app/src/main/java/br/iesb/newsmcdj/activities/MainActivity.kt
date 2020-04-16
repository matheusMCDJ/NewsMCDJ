package br.iesb.newsmcdj.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.iesb.newsmcdj.R
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

        showNews()
    }

    private fun showNews() {
        viewModel.result.observe(this, Observer { news ->
            var str = ""
            news.forEach { n -> str = str.plus("\n\nNoticia: ${n.title}\nImagem: ${n.image}") }
            tvNews.text = str
        })

        viewModel.topHeadLines()
    }

}

