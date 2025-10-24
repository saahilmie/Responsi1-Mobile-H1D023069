package com.example.responsi1mobileh1d023069

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.responsi1mobileh1d023069.databinding.ActivityMainBinding
import com.example.responsi1mobileh1d023069.viewmodel.TeamViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TeamViewModel

    companion object {
        const val TEAM_ID = 110 // SS Lazio
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        setupObservers()
        setupClickListeners()

        viewModel.loadTeamData(TEAM_ID)
    }

    private fun setupObservers() {
        viewModel.teamData.observe(this) { team ->
            Glide.with(this)
                .load(team.crest)
                .into(binding.ivClubLogo)

            binding.tvClubName.text = team.name

            val clubInfo = """
                SS Lazio, founded in 1900 in Rome, is one of Italy's oldest and most iconic football clubs. Known for its sky-blue colors and eagle emblem, Lazio has won major honors including Serie A titles, Coppa Italia trophies, and the UEFA Super Cup. The club shares its home ground, Stadio Olimpico, with AS Roma and has a passionate fanbase known as I Biancocelesti.
            """.trimIndent()
            binding.tvClubInfo.text = clubInfo
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.contentLayout.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners() {
        binding.cvClubHistory.setOnClickListener {
            val intent = Intent(this, ClubHistoryActivity::class.java)
            val team = viewModel.teamData.value
            if (team != null) {
                intent.putExtra("TEAM_DATA", team)
            }
            startActivity(intent)
        }

        binding.cvHeadCoach.setOnClickListener {
            val intent = Intent(this, HeadCoachActivity::class.java)
            val coach = viewModel.teamData.value?.coach
            if (coach != null) {
                intent.putExtra("COACH_DATA", coach)
            }
            startActivity(intent)
        }

        binding.cvTeamSquad.setOnClickListener {
            val intent = Intent(this, TeamSquadActivity::class.java)
            val squad = viewModel.teamData.value?.squad
            if (squad != null) {
                intent.putParcelableArrayListExtra("SQUAD_DATA", ArrayList(squad))
            }
            startActivity(intent)
        }
    }
}
