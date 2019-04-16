package com.anb.screeningtestkotlin.ui.Map

import com.anb.screeningtestkotlin.ui.Base.BasePresenter
import com.anb.screeningtestkotlin.ui.Event.EventPresenter
import com.anb.screeningtestkotlin.data.model.Event
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class MapsPresenter<V: MapsContract.MapsView>(var listEvent: ArrayList<Event>) : BasePresenter<V>(), MapsContract.MapsPresenter<V>{

    lateinit var mGoogleMap: GoogleMap

    override fun initListEvent() {
        listEvent = EventPresenter.listEvent
    }

    override fun initGoogle(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun initMarker() {
        for (event in listEvent) {
            mGoogleMap!!.addMarker(MarkerOptions().position(LatLng(event.laT, event.lonG)).title(event.nama))
        }
    }

    override fun initFocusCamera() {
        changeFocusCamera(LatLng(listEvent[0].laT, listEvent[0].lonG))
    }

    override fun initSetMapsAdapter() {
        getView().setMapsAdapter(listEvent)
    }

    override fun changeFocusCamera(latLng: LatLng) {
        val cameraPosition = CameraPosition.builder().target(latLng).zoom(14f).bearing(0f).tilt(0f).build()
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun changeMarker(position: Int) {
        val marker = mGoogleMap!!.addMarker(MarkerOptions()
                .position(LatLng(listEvent[position].laT, listEvent[position].lonG))
                .title(listEvent[position].nama))
        marker.showInfoWindow()
        changeFocusCamera(marker.position)
    }
}