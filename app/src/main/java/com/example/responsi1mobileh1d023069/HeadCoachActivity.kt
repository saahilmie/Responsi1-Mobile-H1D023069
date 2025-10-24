package com.example.responsi1mobileh1d023069

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023069.databinding.ActivityHeadCoachBinding
import com.example.responsi1mobileh1d023069.model.Coach

class HeadCoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeadCoachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Head Coach"

        val coach = intent.getParcelableExtra<Coach>("COACH_DATA")

        coach?.let {
            binding.tvCoachName.text = it.name ?: "N/A"
            binding.tvCoachNationality.text = "Nationality: ${it.nationality ?: "N/A"}"
            binding.tvCoachDob.text = "Date of Birth: ${it.dateOfBirth ?: "N/A"}"

            it.contract?.let { contract ->
                binding.tvCoachContractStart.text = "Contract Start: ${contract.start ?: "N/A"}"
                binding.tvCoachContractEnd.text = "Contract Until: ${contract.until ?: "N/A"}"
            } ?: run {
                binding.tvCoachContractStart.text = "Contract Start: N/A"
                binding.tvCoachContractEnd.text = "Contract Until: N/A"
            }

            val coachInfo = """
                ${it.name} is the current head coach of SS Lazio. 
                
                Under their guidance, the team continues to compete at the highest level in Serie A and European competitions.
                
                The coaching staff works tirelessly to develop both tactical awareness and individual player skills, maintaining Lazio's competitive edge in Italian football.
            """.trimIndent()

            binding.tvCoachInfo.text = coachInfo
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}