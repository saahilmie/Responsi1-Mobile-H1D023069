package com.example.responsi1mobileh1d023069.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.responsi1mobileh1d023069.databinding.BottomSheetPlayerDetailBinding
import com.example.responsi1mobileh1d023069.model.Player
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson

class PlayerDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetPlayerDetailBinding? = null
    private val binding get() = _binding!!

    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val json = arguments?.getString(ARG_PLAYER_JSON)
        player = json?.let { Gson().fromJson(it, Player::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player?.let { p ->
            binding.tvPlayerNameDetail.text = p.name
            binding.tvPlayerDobDetail.text = "${p.dateOfBirth ?: "N/A"}"
            binding.tvPlayerNationalityDetail.text = "${p.nationality ?: "N/A"}"
            binding.tvPlayerPositionDetail.text = "${p.position ?: "Unknown"}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PLAYER_JSON = "arg_player_json"

        fun newInstance(player: Player): PlayerDetailBottomSheet {
            val args = Bundle().apply {
                putString(ARG_PLAYER_JSON, Gson().toJson(player))
            }
            val fragment = PlayerDetailBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }
}