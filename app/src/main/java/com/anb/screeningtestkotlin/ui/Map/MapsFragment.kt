package com.anb.screeningtestkotlin.ui.Map

/**
 * Created by Agung Nursatria on 5/20/2018.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anb.screeningtestkotlin.R

import com.anb.screeningtestkotlin.adapter.MapAdapter
import com.anb.screeningtestkotlin.di.component.DaggerActivityComponent
import com.anb.screeningtestkotlin.data.model.Event
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.*

import java.util.ArrayList
import javax.inject.Inject

class MapsFragment : Fragment(), OnMapReadyCallback, ViewPager.OnPageChangeListener, MapsContract.MapsView {

    @Inject
    lateinit var MPresenter : MapsPresenter<MapsContract.MapsView>
    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(R.layout.fragment_map, container, false)

        DaggerActivityComponent.builder()
                .build()
                .inject(this)

        MPresenter.initListEvent()
        MPresenter.onAttach(this)
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        if (maps != null) {
            maps.onCreate(null)
            maps.onResume()
            maps.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        MapsInitializer.initialize(context)

        MPresenter.initGoogle(googleMap)
        MPresenter.initMarker()
        MPresenter.initFocusCamera()
        MPresenter.initSetMapsAdapter()
        mapsPager.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        MPresenter.changeMarker(position)
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun setMapsAdapter(listEvent: ArrayList<Event>) {
        mapsPager.adapter = MapAdapter(activity, listEvent)
    }
}
