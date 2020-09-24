package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.example.myapplication.model.CountryInfo
import com.example.myapplication.model.CountryListAdapter
import com.example.myapplication.model.CountryModel
import com.leo.simplearcloader.SimpleArcLoader
import kotlinx.android.synthetic.main.activity_track_country.*
import org.json.JSONException
import org.json.JSONObject


class TrackCountryActivity : AppCompatActivity() {

    private lateinit var searchETX : EditText
    private lateinit var countryListView: ListView
    private lateinit var arcLoader: SimpleArcLoader
    private var requestQueue: RequestQueue? = null
    private var countryModels : MutableList<CountryModel> = arrayListOf()
    private lateinit var adapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_country)
        initViews()
        adapter = CountryListAdapter(this, R.layout.country_item, countryModels)
        countryListView.adapter = adapter
        loader.start()
        jsonParse()

        countryListView.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this, countryModels.get(position).country.toString(), Toast.LENGTH_SHORT).show()

            var intent = Intent(this, CountryDetailsActivity::class.java)
            intent.putExtra("Model", countryModels.get(position))
            startActivity(intent)
        }

        searchETX.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.getFilter().filter(s)
            }

            override fun afterTextChanged(s: Editable) {
                adapter.getFilter().filter(s);
            }
        })
    }

    private fun initViews(){
        searchETX = findViewById(R.id.search_ETX)
        countryListView = findViewById(R.id.countryList)
        countryListView.setTextFilterEnabled(true)
        arcLoader = findViewById(R.id.loader)
        requestQueue = Volley.newRequestQueue(this)
    }

    override fun onBackPressed() {
        finish()
    }

    private fun jsonParse() {

        val url = "https://disease.sh/v3/covid-19/countries"
        val request = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            try {

                for (i in 0 until response.length()) {
                    var countryModel: CountryModel = setModel(response.getJSONObject(i))
                    countryModels.add(i, countryModel)
                }

                adapter.notifyDataSetChanged()
                loader.visibility = View.GONE
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            error.printStackTrace()
            loader.visibility = View.GONE
            loader.visibility = View.GONE
        })
        requestQueue?.add(request)
    }

    private fun setModel(model: JSONObject) : CountryModel{
        var countryModel = CountryModel()
        countryModel.updated = model.getLong("updated")
        countryModel.country = model.getString("country")

        countryModel.cases = model.getInt("cases")
        countryModel.todayCases = model.getInt("todayCases")
        countryModel.deaths = model.getInt("deaths")
        countryModel.todayDeaths = model.getInt("todayDeaths")
        countryModel.recovered = model.getInt("recovered")
        countryModel.todayRecovered = model.getInt("todayRecovered")
        countryModel.active = model.getInt("active")
        countryModel.critical = model.getInt("critical")
        countryModel.casesPerOneMillion = model.getInt("casesPerOneMillion")
        countryModel.deathsPerOneMillion = model.getInt("deathsPerOneMillion")
        countryModel.tests = model.getInt("tests")
        countryModel.testsPerOneMillion = model.getInt("testsPerOneMillion")
        countryModel.population = model.getLong("population")
        countryModel.oneCasePerPeople = model.getInt("oneCasePerPeople")
        countryModel.oneDeathPerPeople = model.getInt("oneDeathPerPeople")
        countryModel.oneTestPerPeople = model.getInt("oneTestPerPeople")
        countryModel.activePerOneMillion = model.getDouble("activePerOneMillion")
        countryModel.recoveredPerOneMillion = model.getDouble("recoveredPerOneMillion")
        countryModel.criticalPerOneMillion = model.getDouble("criticalPerOneMillion")

        var info: JSONObject = model.getJSONObject("countryInfo")
        countryModel.countryInfo = setCountryInfo(info)

        return countryModel
    }

    private fun setCountryInfo(info: JSONObject): CountryInfo{
        var countryInfo = CountryInfo()
        countryInfo.id = info.getString("_id")
        countryInfo.lat = info.getInt("lat")
        countryInfo.long = info.getInt("long")
        countryInfo.flag = info.getString("flag")

        return countryInfo
    }
}