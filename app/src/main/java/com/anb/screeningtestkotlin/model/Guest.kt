package com.anb.screeningtestkotlin.model

/**
 * Created by Agung Nursatria on 5/20/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Guest : RealmObject() {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("birthdate")
    @Expose
    var birthday: String? = null

    var image: Int = 0

}