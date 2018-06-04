package com.anb.screeningtestkotlin.ui.Event

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.anb.screeningtestkotlin.ui.Base.BaseActivity
import com.anb.screeningtestkotlin.ui.Map.MapsActivity
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.adapter.EventAdapter
import com.anb.screeningtestkotlin.di.component.DaggerActivityComponent
import com.anb.screeningtestkotlin.model.Event
import kotlinx.android.synthetic.main.activity_event.*

import java.util.*
import javax.inject.Inject

class EventActivity : BaseActivity(),EventContract.EventView {

    @Inject
    lateinit var EPresenter : EventPresenter<EventContract.EventView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setSupportActionBar(toolbarEvent)

        DaggerActivityComponent.builder()
                .build()
                .inject(this)

        EPresenter.onAttach(this)
        EPresenter.initData()
        event_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> toSelectingLayout(view) }
        btnBack.setOnClickListener { finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.event_menu, menu)
        return true
    }

    override fun seEventAdapter(listEvent: ArrayList<Event>) {
        event_list.adapter = EventAdapter(this@EventActivity, listEvent)
    }

    override fun toSelectingLayout(view : View) {
        val returnIntent = Intent()
        val txtNamaEvent = view.findViewById(R.id.txttNamaEvent) as TextView
        returnIntent.putExtra("namaevent", txtNamaEvent.text)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun toMapLayout() {
        val intent = Intent(this@EventActivity, MapsActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_newmedia_event) {
            toMapLayout()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED, Intent())
        super.onBackPressed()
    }
}
