package com.example.myapplication.model

import java.io.Serializable

class CountryInfo : Serializable{
    var id: String? = null
    var lat: Int? = null
    var long: Int? = null
    var flag: String? = null

    override fun toString(): String {
        return "CountryInfo(id=$id, lat=$lat, long=$long, flag=$flag)"
    }
}