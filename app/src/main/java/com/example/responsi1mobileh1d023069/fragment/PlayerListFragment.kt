package com.example.responsi1mobileh1d023069.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023069.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023069.databinding.FragmentPlayerListBinding
import com.example.responsi1mobileh1d023069.model.Player

class PlayerListFragment : Fragment() {

    private var _binding: FragmentPlayerListBinding? = null
    private val binding get() = _binding!!

    private lateinit var playerAdapter: PlayerAdapter
    private var players: ArrayList<Player> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        players = arguments?.getParcelableArrayList(ARG_PLAYERS) ?: arrayListOf()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter(players)
        binding.rvPlayers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playerAdapter
        }

        // Show empty state if no players
        if (players.isEmpty()) {
            binding.tvEmptyState.visibility = View.VISIBLE
            binding.rvPlayers.visibility = View.GONE
        } else {
            binding.tvEmptyState.visibility = View.GONE
            binding.rvPlayers.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PLAYERS = "players"

        fun newInstance(players: ArrayList<Player>): PlayerListFragment {
            val fragment = PlayerListFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PLAYERS, players)
            fragment.arguments = args
            return fragment
        }
    }
}