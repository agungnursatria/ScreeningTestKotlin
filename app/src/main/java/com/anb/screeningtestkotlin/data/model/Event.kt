package com.anb.screeningtestkotlin.data.model

import java.util.ArrayList

/**
 * Created by Agung Nursatria on 5/20/2018.
 */

data class Event(val image: Int, val nama: String, val tgl: String, val hastag: ArrayList<String>, val desc: String, val laT: Double, val lonG: Double) {}