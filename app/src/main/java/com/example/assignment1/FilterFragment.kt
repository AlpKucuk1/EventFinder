package com.example.assignment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assignment1.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        // Set up button click listeners
        binding.filterByDateButton.setOnClickListener {
            // Handle filter by date logic
        }

        binding.filterByLocationButton.setOnClickListener {
            // Handle filter by location logic
        }

        return binding.root
    }
}
