package com.example.unleeg8.View.ui.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.unleeg8.R
import com.example.unleeg8.databinding.FragmentShipBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig

/*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

class ShipFragment : Fragment(), OnMapReadyCallback {

  /*  private var param1: String? = null
    private var param2: String? = null*/

    lateinit var googleMap: GoogleMap
    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    lateinit var mapView: MapView

//    var _binding: FragmentShipBinding?=null
//    val binding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_ship, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Identifica su aplicación de forma exclusiva para los servidores de mosaicos

        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID)

     /*   arguments?.let{
            param1=it.getString(ARG_PARAM1)
            param2= it.getString(ARG_PARAM2)
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       //Google Maps
        val mapFragment= this.childFragmentManager.findFragmentById(R.id.mapGoogle) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Open Street Maps
        mapView= view.findViewById(R.id.mapOpenStreet)
        mapView.setTileSource(TileSourceFactory.MAPNIK)

        //Open Street Map Location
        val geoPoint= GeoPoint(6.2046615,-75.400961)
        val mapController= mapView.controller
        mapController.setZoom(16.0)
        mapController.setCenter(geoPoint)

        //Marcador
        val marker= Marker(mapView)
        marker.setPosition(geoPoint)
        marker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_BOTTOM)
        marker.setTitle("UnLeeG8")
        mapView.overlays.add(marker)

    }

    override fun onMapReady(map: GoogleMap) {
        val colombia= LatLng(5.070275,-75.513817)
        map?.let {
            this.googleMap= it
            map.addMarker(
                MarkerOptions()
                .position(colombia)
                .title("Manizales UnLeeG8")
            )
        }
        enableLocation()
    }

    fun isLocationPermissionGrated()=ContextCompat.checkSelfPermission(
        this.requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION
    )==PackageManager.PERMISSION_GRANTED

    fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),
            android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this.context,"Activar permisos de ubicación",Toast.LENGTH_LONG).show()
        }else{
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            com.example.unleeg8.View.ui.fragments.ShipFragment.Companion.REQUEST_CODE_LOCATION
                )
        }
    }

    @SuppressLint("MissingPermission")
    fun enableLocation(){
        if(!::googleMap.isInitialized)return
        if(isLocationPermissionGrated())
            googleMap.isMyLocationEnabled=true
        else
            requestLocationPermission()
    }



}