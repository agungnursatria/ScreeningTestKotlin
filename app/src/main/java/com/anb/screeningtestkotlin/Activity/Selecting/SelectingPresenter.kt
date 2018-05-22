package com.anb.screeningtestkotlin.Activity.Selecting

import android.text.format.DateFormat
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class SelectingPresenter(selectingView : SelectingContract.SelectingView): SelectingContract.SelectingPresenter{

    val SView = selectingView

    override fun isMonthPrime(dateString: String) {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = sdf.parse(dateString)
            val month = Integer.valueOf(DateFormat.format("MM", date) as String)!!

            var counter = 0
            for (i in 2..month) {
                if (month % i == 0)
                    counter++
            }
            val prime = if (counter == 1) "Bulan $month merupakan bilangan prima" else "Bulan $month bukan bilangan prima"
            SView.showToast(prime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun typeOS(dateString: String) {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val date = sdf.parse(dateString)
            val startDate = (date.time / 1000).toInt()

            val os: String
            if (startDate % 2 == 0 && startDate % 3 == 0) {
                os = "iOS"
            } else if (startDate % 3 == 0) {
                os = "android"
            } else if (startDate % 2 == 0) {
                os = "blackberry"
            } else {
                os = "feature phone"
            }
            SView.showToast(os)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}