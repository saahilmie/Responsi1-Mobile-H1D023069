package com.example.responsi1mobileh1d023069.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023069.databinding.ItemPlayerBinding
import com.example.responsi1mobileh1d023069.model.Player
import com.example.responsi1mobileh1d023069.model.PositionColor
import com.example.responsi1mobileh1d023069.ui.PlayerDetailBottomSheet

class PlayerAdapter(private val players: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int = players.size

    class PlayerViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.apply {
                tvPlayerName.text = player.name
                tvPlayerNationality.text = player.nationality ?: "N/A"

                val colorString = PositionColor.getColor(player.position)
                try {
                    playerContainer.setBackgroundColor(Color.parseColor(colorString))
                } catch (e: Exception) {
                    playerContainer.setBackgroundColor(Color.parseColor("#9E9E9E"))
                }

                playerContainer.setOnClickListener {
                    val context = binding.root.context
                    if (context is AppCompatActivity) {
                        val bottomSheet = PlayerDetailBottomSheet.newInstance(player)
                        bottomSheet.show(context.supportFragmentManager, "player_detail")
                    }
                }
            }
        }
    }
}