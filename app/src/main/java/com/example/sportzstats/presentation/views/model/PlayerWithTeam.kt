package com.example.sportzstats.presentation.views.model

import android.os.Parcelable
import com.example.sportzstats.domain.model.Player

import kotlinx.parcelize.Parcelize

@Parcelize
data class SquadData(
    val player: Player,
    val teamName: String
) : Parcelable
