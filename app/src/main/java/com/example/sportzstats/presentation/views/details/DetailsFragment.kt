package com.example.sportzstats.presentation.views.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportzstats.R
import com.example.sportzstats.databinding.FragmentDetailsBinding
import com.example.sportzstats.presentation.views.adapter.PlayersAdapter
import com.example.sportzstats.presentation.views.model.SquadData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var allPlayers: List<SquadData>
    private lateinit var teamA: List<SquadData>
    private lateinit var teamB: List<SquadData>
    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val post = arguments?.let {
            DetailsFragmentArgs.fromBundle(it).post
        }

        binding.chipTeamOne.text = post?.teams?.teamOne?.nameFull
        binding.chipTeamTwo.text = post?.teams?.teamTwo?.nameFull

        val teamOnePlayers = post?.teams?.teamOne?.players?.values?.map {
            SquadData(it, post.teams.teamOne.nameShort)
        } ?: emptyList()

        val teamTwoPlayers = post?.teams?.teamTwo?.players?.values?.map {
            SquadData(it, post.teams.teamTwo.nameShort)
        } ?: emptyList()

        allPlayers = teamOnePlayers + teamTwoPlayers
        teamA = teamOnePlayers
        teamB = teamTwoPlayers

        playersAdapter = PlayersAdapter(requireActivity(), allPlayers)
        binding.recyclerViewPlayers.adapter = playersAdapter

        binding.recyclerViewPlayers.layoutManager = LinearLayoutManager(requireContext())

        binding.chipGroupPlayers.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_all_squad -> {
                    playersAdapter = PlayersAdapter(requireActivity(), allPlayers)
                }

                R.id.chip_team_one -> {
                    playersAdapter = PlayersAdapter(requireActivity(), teamA)
                }

                R.id.chip_team_two -> {
                    playersAdapter = PlayersAdapter(requireActivity(), teamB)
                }
            }
            binding.recyclerViewPlayers.adapter = playersAdapter
        }
    }
}