package com.example.covidcountriesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchCountries {
            if (it != null) {
                rvCountries.apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = CountriesAdapter(it)
                }
            }
        }
    }

    private fun fetchCountries(callback: (countries: List<Country>?) -> Unit) {
        val countriesService = ServiceBuilder.buildService(CountryService::class.java)
        val request = countriesService.getAffectedCountryList()

        request.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    val countriesList = response.body()
                    callback(countriesList)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT)
                    .show()
                callback(null)
            }
        })
    }
}