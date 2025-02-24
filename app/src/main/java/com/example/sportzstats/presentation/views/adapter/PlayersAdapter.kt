package com.example.sportzstats.presentation.views.adapter

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzstats.R
import com.example.sportzstats.databinding.ItemPlayerBinding
import com.example.sportzstats.presentation.utils.Team
import com.example.sportzstats.presentation.views.model.SquadData
import com.example.sportzstats.presentation.views.playerDetails.PlayerDetailsBottomSheetFragment

class PlayersAdapter(private val activity: FragmentActivity, private val squadData: List<SquadData>) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val squadData = squadData[position]
        val playerName = squadData.player.nameFull.split(" ")
        holder.binding.tvPlayerName.text = playerName.getOrNull(0) ?: ""
        holder.binding.tvPlayerLastName.text = playerName.getOrNull(1) ?: ""
        holder.binding.tvAverageValue.text = squadData.player.batting.average
        holder.binding.tvRunsValue.text = squadData.player.batting.runs

        holder.binding.tvBadge.visibility = when {
            squadData.player.isCaptain == true -> {
                holder.binding.tvBadge.text = activity.resources.getString(R.string.str_captain)
                View.VISIBLE
            }
            squadData.player.isKeeper == true -> {
                holder.binding.tvBadge.text = activity.resources.getString(R.string.str_keeper)
                View.VISIBLE
            }
            else -> View.GONE
        }

        val flagImage = if (squadData.teamName == Team.IND.name) {
            R.drawable.ic_flag_india
        } else if (squadData.teamName == Team.PAK.name) {
            R.drawable.ic_flag_pak
        } else if (squadData.teamName == Team.NZ.name) {
            R.drawable.ic_flag_new_zealand
        } else if (squadData.teamName == Team.SA.name) {
            R.drawable.ic_flag_sa
        } else {
            null
        }
        flagImage?.let { holder.binding.ivTeamFlag.setImageResource(it) }

        holder.itemView.setOnClickListener {
            val dialogFragment = PlayerDetailsBottomSheetFragment()
            val bundle = Bundle().apply {
                putParcelable("squad_data", squadData)
            }
            dialogFragment.arguments = bundle
            dialogFragment.show(activity.supportFragmentManager, "player_details_dialog")
        }
    }

    override fun getItemCount(): Int = squadData.size

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)
}
