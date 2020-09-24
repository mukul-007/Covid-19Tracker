package com.example.myapplication.model

import java.io.Serializable

class CountryModel : Serializable {
    var updated: Long? = null
    var country: String? = null
    var countryInfo: CountryInfo? = null
    var cases: Int? = null
    var todayCases: Int? = null
    var deaths: Int? = null
    var todayDeaths: Int? = null
    var recovered: Int? = null
    var todayRecovered: Int? = null
    var active: Int? = null
    var critical: Int? = null
    var casesPerOneMillion: Int? = null
    var deathsPerOneMillion: Int? = null
    var tests: Int? = null
    var testsPerOneMillion: Int? = null
    var population: Long? = null
    var oneCasePerPeople: Int? = null
    var oneDeathPerPeople: Int? = null
    var oneTestPerPeople: Int? = null
    var activePerOneMillion: Double? = null
    var recoveredPerOneMillion: Double? = null
    var criticalPerOneMillion: Double? = null

    override fun toString(): String {
        return "CountryInfo(updated=$updated, country=$country, countryInfo=$countryInfo, cases=$cases, todayCases=$todayCases, deaths=$deaths, todayDeaths=$todayDeaths, recovered=$recovered, todayRecovered=$todayRecovered, active=$active, critical=$critical, casesPerOneMillion=$casesPerOneMillion, deathsPerOneMillion=$deathsPerOneMillion, tests=$tests, testsPerOneMillion=$testsPerOneMillion, population=$population, oneCasePerPeople=$oneCasePerPeople, oneDeathPerPeople=$oneDeathPerPeople, oneTestPerPeople=$oneTestPerPeople, activePerOneMillion=$activePerOneMillion, recoveredPerOneMillion=$recoveredPerOneMillion, criticalPerOneMillion=$criticalPerOneMillion)"
    }
}