package com.example.responsi1mobileh1d023069

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023069.databinding.ActivityClubHistoryBinding
import com.example.responsi1mobileh1d023069.model.TeamResponse

class ClubHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClubHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Club History"
        supportActionBar?.subtitle = "SS Lazio"

        val teamData = intent.getParcelableExtra("TEAM_DATA") as? TeamResponse

        val historyText = """
            SS Lazio, a renowned Italian football club based in Rome, has a rich history, passionate supporters, and a strong presence in the football world. In this comprehensive article, we will delve into the fascinating journey of SS Lazio, exploring its captivating history, unique colors, badge, and nicknames, as well as the iconic stadium where the team creates unforgettable moments. Our exploration will extend to the dedicated supporters and fierce rivalries that define the club’s identity. We will provide an in-depth look at the current squad, other players under contract, and those out on loan, shedding light on the promising talent within the youth sector.

            Introduction to SS Lazio
            Società Sportiva Lazio, commonly referred to as SS Lazio, is a professional football club based in Rome, Italy, competing in the Serie A, the top tier of Italian football. The club has a rich history, passionate supporters, and a strong presence in the city’s football landscape.

            Founded in 1900, SS Lazio has been an integral part of Rome’s football culture, sharing the spotlight with its city rival AS Roma. The club’s blue and white colors, reflecting the city’s traditional emblematic colors, have become iconic in the Serie A arena. Throughout its history, SS Lazio has celebrated various successes, including domestic league titles, Coppa Italia victories, and notable contributions to European football.

            History of SS Lazio
            The history of SS Lazio is intertwined with the cultural fabric of Rome, with the club’s origins dating back to its foundation in 1900. Over the years, the team has experienced significant victories, memorable matches, and enduring rivalries that have shaped its identity within Italian football.

            SS Lazio has left an indelible mark on Italian football history. One of the pivotal moments in the club’s journey came in 1974 when they secured their first Serie A title. This achievement elevated the club’s status and captivated fans with their remarkable display of skill and determination on the field.

            Another iconic milestone was the triumphant victory in the 1999 UEFA Super Cup, solidifying their presence on the international stage. The unparalleled talents of players like Alessandro Nesta and Giuseppe Signori have contributed to the club’s storied legacy, etching their names in the annals of football history.

            Colours, Badge, and Nicknames
            SS Lazio is distinguished by its iconic colours of sky blue and white, reflected in the club’s badge and embraced by its loyal fanbase. The club has earned the moniker ‘I Biancocelesti’ (The White and Sky Blues) as a testament to its distinctive identity within Italian football.

            The club’s badge, featuring the iconic eagle, further enriches its visual identity. The eagle, a powerful and revered symbol in Roman mythology, embodies strength and determination, reflecting the club’s competitive spirit.

            Stadium
            SS Lazio calls the Stadio Olimpico its home ground, a renowned stadium in Rome that has witnessed countless historic moments and thrilling matches for the club. The stadium’s atmosphere resonates with the passion of Lazio’s supporters, creating an electric environment for every game.

            Supporters and Rivalries
            The fanbase of SS Lazio, including passionate ultras groups, embodies unwavering support and fervent loyalty to the club, amplifying the electric atmosphere during the ‘Derby della Capitale’ and other pivotal matches. Lazio’s rivalries with AS Roma and other prominent Serie A teams add an intense competitive edge to the club’s fixtures.

            Statistics and Records
            The statistics and records of SS Lazio encapsulate the club’s memorable milestones, remarkable achievements, and historical benchmarks, reflecting its enduring impact on Italian football.
        """.trimIndent()

        binding.tvHistoryContent.text = historyText
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}