package com.example.myapplication.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R

class CountryListAdapter(var mCntx: Context, var resource: Int, var items: List<CountryModel>):ArrayAdapter<CountryModel>(
    mCntx,
    resource,
    items
){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCntx)
        val view: View = layoutInflater.inflate(resource, null)

        val name: TextView = view.findViewById(R.id.countryName)
        val image: ImageView = view.findViewById(R.id.flagImage)

        var mItem: CountryModel = items[position]
        name.text = mItem.country
        Glide.with(mCntx)
            .load(mItem.countryInfo?.flag)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(image)

        return view
    }

}