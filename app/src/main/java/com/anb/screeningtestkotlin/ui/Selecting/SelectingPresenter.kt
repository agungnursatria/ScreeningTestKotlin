package com.anb.screeningtestkotlin.ui.Selecting

import android.text.format.DateFormat
import com.anb.screeningtestkotlin.ui.Base.BasePresenter
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

class SelectingPresenter<V: SelectingContract.SelectingView>: BasePresenter<V>(), SelectingContract.SelectingPresenter<V>{

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
            getView().showToast(prime)
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
            getView().showToast(os)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}