package com.example.covidcountriesapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidcountriesapi.R
import com.example.covidcountriesapi.models.Country
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter(
    private val countries: List<Country>
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countries[position]
        holder.itemView.apply {
            tvTitle.text = country.country
            tvCases.text = country.cases.toString()
            Picasso.get().load(country.countryInfo.flag).into(ivFlag)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}