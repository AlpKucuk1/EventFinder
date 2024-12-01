package com.example.assignment1

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment1.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var events: List<Event>
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        // Load events from CSV
        events = loadEventsFromCsv()

        // Set up RecyclerView with the EventDetails adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = EventDetails(events) { event ->
            // Navigate to the EventDetailFragment
            val action = MainFragmentDirections.actionMainFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }

        // Sort button popup for filtering events
        binding.sortButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(requireContext(), view)
            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.filter_name -> {
                        filterEvents("Name")
                        true
                    }
                    R.id.filter_city -> {
                        filterEvents("City")
                        true
                    }
                    R.id.filter_date -> {
                        filterEvents("Date")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        // Navigate to Event List Fragment
        binding.navigateToEventListButton.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_eventListFragment)
        }

        // Navigate to Filter Fragment
        binding.navigateToFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filterFragment)
        }
    }

    private fun filterEvents(criteria: String) {
        val filteredEvents = when (criteria) {
            "Name" -> events.sortedBy { it.name }
            "City" -> events.sortedBy { it.location }
            "Date" -> events.sortedBy { it.date }
            else -> events
        }
        binding.recyclerView.adapter = EventDetails(filteredEvents) { event ->
            // Navigate to the EventDetailFragment
            val action = MainFragmentDirections.actionMainFragmentToEventDetailFragment(event)
            findNavController().navigate(action)
        }
    }

    private fun loadEventsFromCsv(): List<Event> {
        val events = mutableListOf<Event>()
        try {
            val inputStream = requireActivity().assets.open("events/MOCK_DATA.csv")
            inputStream.bufferedReader().useLines { lines ->
                lines.drop(1).forEach { line ->
                    val tokens = line.split(",")
                    if (tokens.size >= 5) {
                        val event = Event(
                            id = tokens[0].toInt(),
                            name = tokens[1],
                            location = tokens[2],
                            date = tokens[3],
                            time = tokens[4],
                            description = tokens[5]
                        )
                        events.add(event)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return events
    }
}
