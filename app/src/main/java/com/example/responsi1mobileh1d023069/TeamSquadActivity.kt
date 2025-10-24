package com.example.responsi1mobileh1d023069

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023069.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023069.databinding.ActivityTeamSquadBinding
import com.example.responsi1mobileh1d023069.model.Player

class TeamSquadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamSquadBinding
    private var squadData: ArrayList<Player> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Team Squad"

        squadData = intent.getParcelableArrayListExtra("SQUAD_DATA") ?: arrayListOf()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val sortedSquad = squadData.sortedWith(compareBy { player ->
            when (player.position?.uppercase()) {
                "GOALKEEPER" -> 1
                "DEFENCE" -> 2
                "MIDFIELD" -> 3
                "OFFENCE" -> 4
                else -> 5
            }
        })

        val adapter = PlayerAdapter(sortedSquad)
        binding.rvAllPlayers.apply {
            layoutManager = LinearLayoutManager(this@TeamSquadActivity)
            this.adapter = adapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}