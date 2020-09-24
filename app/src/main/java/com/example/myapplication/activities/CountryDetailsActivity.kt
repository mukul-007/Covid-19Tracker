package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.model.CountryModel

class CountryDetailsActivity : AppCompatActivity() {

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
    private lateinit var imageView: ImageView
    private lateinit var name: TextView
    private lateinit var model : CountryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        var intent: Intent = intent
        model = intent.getSerializableExtra("Model") as CountryModel

        initView()
        setViews()
    }

    fun initView(){
        imageView = findViewById(R.id.flagImage_details)
        name = findViewById(R.id.countryName_details)
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
    }

    fun setViews(){
        Glide.with(this)
            .load(model.countryInfo?.flag)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        name.text = model.country

        totalCases.text = model.cases.toString()
        activeCases.text = model.active.toString()
        recovered.text = model.recovered.toString()
        deaths.text = model.deaths.toString()
        critical.text = model.critical.toString()
        todayCases.text = model.todayCases.toString()
        todayRecovered.text = model.todayRecovered.toString()
        todayDeaths.text = model.todayDeaths.toString()
        casesPerMillion.text = model.casesPerOneMillion.toString()
        activePerMillion.text = model.activePerOneMillion.toString()
        recoveredPerMillion.text = model.recoveredPerOneMillion.toString()
        deathsPerMillion.text = model.deathsPerOneMillion.toString()

    }
}