package com.sachin.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.weatherapp.R
import com.sachin.weatherapp.databinding.FragmentCityBinding
import com.sachin.weatherapp.ui.adapters.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment(R.layout.fragment_city) {

    lateinit var binding: FragmentCityBinding
    private var cityAdapter: CitiesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCityBinding.inflate(inflater, container, false)

        var citiesList: ArrayList<String> = ArrayList()
        citiesList.add("Bangalore")
        citiesList.add("Mumbai")
        citiesList.add("Pune")
        citiesList.add("Belgaum")
        citiesList.add("Chennai")
        citiesList.add("Hyderabad")

        val bundle = Bundle()

        cityAdapter = CitiesAdapter(citiesList) { city ->
            bundle.putString("city", city)
            findNavController().navigate(R.id.action_cityFragment_to_detailFragment, bundle)
        }
        // Inflate the layout for this fragment
        binding.rvCities.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = cityAdapter
        }
        return binding.root
    }

}