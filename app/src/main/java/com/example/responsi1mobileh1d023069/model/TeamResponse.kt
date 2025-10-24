package com.example.responsi1mobileh1d023069.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamResponse(
    val area: Area,
    val id: Int,
    val name: String,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val address: String?,
    val website: String?,
    val founded: Int?,
    val clubColors: String?,
    val venue: String?,
    val coach: Coach?,
    val squad: List<Player> = emptyList()
) : Parcelable

@Parcelize
data class Area(
    val id: Int?,
    val name: String?,
    val code: String?,
    val flag: String?
) : Parcelable

@Parcelize
data class Coach(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val contract: Contract?
) : Parcelable

@Parcelize
data class Contract(
    val start: String?,
    val until: String?
) : Parcelable

@Parcelize
data class Player(
    val id: Int,
    val name: String,
    val position: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val shirtNumber: Int?,
    val section: String?
) : Parcelable

// Menentukan warna berdasarkan posisi
object PositionColor {
    fun getColor(position: String?): String {
        return when (position?.uppercase()) {
            "GOALKEEPER" -> "#FFC107" // Kuning
            "DEFENCE" -> "#2196F3" // Biru
            "MIDFIELD" -> "#4CAF50" // Hijau
            "OFFENCE" -> "#F44336" // Merah
            else -> "#9E9E9E" // Abu-abu untuk posisi tidak dikenal
        }
    }
}