package com.anb.screeningtestkotlin.Activity.Map

import com.anb.screeningtestkotlin.model.Event
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/22/2018.
 */

class MapsContract{
    interface MapsView{
        fun setMapsAdapter(listEvent: ArrayList<Event>)
    }

    interface MapsPresenter{
        fun initListEvent()
        fun initGoogle(googleMap: GoogleMap)
        fun initMarker()
        fun initFocusCamera()
        fun initSetMapsAdapter()
        fun changeMarker(position: Int)
        fun changeFocusCamera(latLng: LatLng)
    }
}