package br.iesb.newsmcdj.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.iesb.newsmcdj.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        //inicia a conexao com a google para fazer download dos pedaços do mapa, pra fazer um cache local
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //Guardou a instancia do mapa nessa variável
        mMap = googleMap

        //Criou um método para pegar latitude e longitude
        val sydney = LatLng(-12.9393877, -52.6244491)
        //Adicionou um marcador na instancia do mapa com titulo de sydney
        mMap.addMarker(MarkerOptions().position(sydney).title("Marcado no Brasil"))
        //Move a camera do mapa para a posição de sydney
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f)) da zoom de 15 em sydney
    }
}