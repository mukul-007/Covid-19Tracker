package com.example.myapplication.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private lateinit var totalCases : TextView
    private lateinit var activeCases : TextView
    private lateinit var recovered : TextView
    private lateinit var deaths : TextView
    private lateinit var critical : TextView
    private lateinit var todayCases : TextView
    private lateinit var todayRecovered : TextView
    private lateinit var todayDeaths : TextView
    private lateinit var casesPerMillion : TextView
    private lateinit var activePerMillion : TextView
    private lateinit var recoveredPerMillion : TextView
    private lateinit var deathsPerMillion : TextView
    private lateinit var affectedCountries : TextView
    private lateinit var pieChart: PieChart
    private var requestQueue: RequestQueue? = null
    private lateinit var trackButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        jsonParse()

        trackButton.setOnClickListener {
            Toast.makeText(this, "Clicked Track Button!", Toast.LENGTH_SHORT).show()
            var intent : Intent = Intent(this, TrackCountryActivity::class.java)
            startActivity(intent)
        }
    }

    fun initView(){
        pieChart = findViewById(R.id.piechart)
        totalCases = findViewById(R.id.totalCases)
        activeCases = findViewById(R.id.activeCases)
        recovered = findViewById(R.id.recovered)
        deaths = findViewById(R.id.deaths)
        critical = findViewById(R.id.critical)
        todayCases = findViewById(R.id.todayCases)
        todayRecovered = findViewById(R.id.todayRecovered)
        todayDeaths = findViewById(R.id.todayDeaths)
        casesPerMillion = findViewById(R.id.casesPerMillion)
        activePerMillion = findViewById(R.id.activePerMillion)
        recoveredPerMillion = findViewById(R.id.recoveredPerMillion)
        deathsPerMillion = findViewById(R.id.deathsPerMillion)
        affectedCountries = findViewById(R.id.affectedCountries)
        requestQueue = Volley.newRequestQueue(this)
        trackButton = findViewById(R.id.trackBTN)
    }

    private fun jsonParse() {
        val url = "https://disease.sh/v3/covid-19/all"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {

            totalCases.text = response.getString("cases")
            activeCases.text = response.getString("active")
            recovered.text = response.getString("recovered")
            deaths.text = response.getString("deaths")
            critical.text = response.getString("critical")
            todayCases.text = response.getString("todayCases")
            todayRecovered.text = response.getString("todayRecovered")
            todayDeaths.text = response.getString("todayDeaths")
            casesPerMillion.text = response.getString("casesPerOneMillion")
            activePerMillion.text = response.getString("activePerOneMillion")
            recoveredPerMillion.text = response.getString("recoveredPerOneMillion")
            deathsPerMillion.text = response.getString("deathsPerOneMillion")
            affectedCountries.text = response.getString("affectedCountries")

            setPieChart()

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }

    fun setPieChart(){

        pieChart.addPieSlice(PieModel("Total Cases",
            totalCases.text.toString().toInt().toFloat(), Color.parseColor("#ff920c")))
        pieChart.addPieSlice(PieModel("Active Cases",
            activeCases.text.toString().toInt().toFloat(), Color.parseColor("#0075cb")))
        pieChart.addPieSlice(PieModel("Recovered",
            recovered.text.toString().toInt().toFloat(), Color.parseColor("#3eea17")))
        pieChart.addPieSlice(PieModel("Deaths",
            deaths.text.toString().toInt().toFloat(), Color.parseColor("#ff0000")))

        pieChart.startAnimation()

    }
}