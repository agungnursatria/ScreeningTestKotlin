package com.anb.screeningtestkotlin.ui.Home

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.anb.screeningtestkotlin.ui.Base.BaseActivity
import com.anb.screeningtestkotlin.R
import com.anb.screeningtestkotlin.di.component.DaggerHomeComponent
import com.anb.screeningtestkotlin.ui.Selecting.SelectingActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.HomeView {

    @Inject
    lateinit var HPresenter : HomePresenter<HomeContract.HomeView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerHomeComponent.builder()
                .build()
                .inject(this)

        HPresenter.onAttach(this)

        btnSelesai.setOnClickListener(View.OnClickListener {
            HPresenter.palindromCheck(edtNama.text.toString())
        })
    }

    override fun showAlert(sentence: String, isPalindrom: String, palindromCheck : Boolean) {
        val builder = AlertDialog.Builder(this@HomeActivity)
        builder.setTitle(palindromCheck.toString().toUpperCase())
                .setMessage(sentence + " " + isPalindrom)
                .setCancelable(false)
                .setPositiveButton("Next", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                    toLayoutSelection(sentence)
                })
        val alert = builder.create()
        alert.show()
        val buttonPositive = alert.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonPositive.setTextColor(Color.parseColor("#000000"))
    }

    override fun toLayoutSelection(name: String) {
        val intent = Intent(this@HomeActivity, SelectingActivity::class.java)
        intent.putExtra("nama", name)
        startActivity(intent)
    }
}
