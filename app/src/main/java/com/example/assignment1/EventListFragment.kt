package com.example.assignment1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.databinding.FragmentEventListBinding
import com.example.assignment1.R

class EventListFragment : Fragment(R.layout.fragment_event_list) {

    private lateinit var binding: FragmentEventListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize binding
        binding = FragmentEventListBinding.bind(view)

        // Sample event list
        val events = listOf(
            Event(1, "Event 1", "Location 1", "2024-11-01", "10:00 AM", "Description 1"),
            Event(2, "Event 2", "Location 2", "2024-11-02", "12:00 PM", "Description 2"),
            // Add more events here
        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = EventDetails(events) { event ->
            val action = EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
    }
}
