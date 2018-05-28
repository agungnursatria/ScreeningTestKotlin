package com.anb.screeningtestkotlin.ui.Selecting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.anb.screeningtestkotlin.ui.Base.BaseActivity
import com.anb.screeningtestkotlin.ui.Event.EventActivity
import com.anb.screeningtestkotlin.ui.Guest.GuestActivity
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.di.component.DaggerSelectingComponent
import kotlinx.android.synthetic.main.activity_selecting.*
import javax.inject.Inject

class SelectingActivity : BaseActivity(), SelectingContract.SelectingView {

    @Inject
    lateinit var SPresenter : SelectingPresenter<SelectingContract.SelectingView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecting)

        DaggerSelectingComponent.builder()
                .build()
                .inject(this)

        SPresenter.onAttach(this)
        val extras = intent.extras
        txtNama.text = extras?.getString("nama")

        btnPilihEvent.setOnClickListener {
            toEventActivity()
        }

        btnPilihGuest.setOnClickListener {
            toGuestActivity()
        }
    }

    override fun toEventActivity() {
        val intent = Intent(this@SelectingActivity, EventActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun toGuestActivity() {
        val intent = Intent(this@SelectingActivity, GuestActivity::class.java)
        startActivityForResult(intent, 2)
    }

    override fun changeBtnPilihEvent(sentence: String) {
        btnPilihEvent.text = sentence
    }

    override fun changeBtnPilihGuest(sentence: String) {
        btnPilihGuest.text = sentence
    }

    override fun showToast(sentence: String) {
        Toast.makeText(this, sentence, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                changeBtnPilihEvent(data.getStringExtra("namaevent"))
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                changeBtnPilihGuest(data.getStringExtra("namaguest"))

                val dateString = data.getStringExtra("birthdayguest")
                SPresenter.typeOS(dateString)

                SPresenter.isMonthPrime(dateString)
            }
        }
    }
}
