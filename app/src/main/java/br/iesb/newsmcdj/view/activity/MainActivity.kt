package br.iesb.newsmcdj.view.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.view.adapter.NewsAdapter
import br.iesb.newsmcdj.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

private const val PERMISSION_REQUEST = 10

class MainActivity : AppCompatActivity() {

    private var permissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    private lateinit var context: Context

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this). get(NewsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        btProfile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        btMapa.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            if(checkPermission(context,permissions)){
                Toast.makeText(context, "A permissao ja foi concedida", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }else{
                requestPermissions(permissions, PERMISSION_REQUEST)
            }


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


    fun checkPermission(context: Context, permissionArray: Array<String>): Boolean{
        var allSuccess = true
        for(i in permissionArray.indices){
            if(checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED){
                allSuccess = false
            }
        }
        return allSuccess
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST){
            var allSuccess = true
            for(i in permissions.indices){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allSuccess = false
                    Toast.makeText(context, "Permissao negada", Toast.LENGTH_SHORT).show()
                }
            }
            if(allSuccess){
                Toast.makeText(context, "Permissao concedida", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

