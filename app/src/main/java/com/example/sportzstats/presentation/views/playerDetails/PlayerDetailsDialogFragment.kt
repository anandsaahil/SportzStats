package com.example.sportzstats.presentation.views.playerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.sportzstats.databinding.LayoutPlayerDetailsBinding

class PlayerDetailsBottomSheetFragment : DialogFragment() {
    private val args: PlayerDetailsBottomSheetFragmentArgs by navArgs()
    lateinit var binding: LayoutPlayerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = LayoutPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        val squadData = args.squadData
        binding.tvPlayerName.text = squadData.player.nameFull.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvStyleValue.text = squadData.player.batting.style?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvAverageValue.text = squadData.player.batting.average?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvRateValue.text = squadData.player.batting.strikeRate?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvRunsValue.text = squadData.player.batting.runs?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvBowlStyleValue.text = squadData.player.bowling.style?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvBowlAverageValue.text = squadData.player.bowling.average?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvEcoRateValue.text = squadData.player.bowling.economyRate?.takeIf { it.isNotEmpty() } ?: "NA"
        binding.tvWicketsValue.text = squadData.player.bowling.wickets?.takeIf { it.isNotEmpty() } ?: "NA"

    }
}