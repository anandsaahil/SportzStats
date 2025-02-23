package com.example.sportzstats.presentation.views.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportzstats.R
import com.example.sportzstats.databinding.FragmentDetailsBinding
import com.example.sportzstats.presentation.views.adapter.PlayersAdapter
import com.example.sportzstats.presentation.viewmodels.PostViewModel
import com.example.sportzstats.presentation.views.model.SquadData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val postViewModel: PostViewModel by viewModels()
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var allPlayers: List<SquadData>
    private lateinit var teamIndiaPlayers: List<SquadData>
    private lateinit var teamNewZealandPlayers: List<SquadData>
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
        val indiaPlayers = post?.teams?.india?.players?.values?.map {
            SquadData(it, post.teams.india.nameShort)
        } ?: emptyList()

        val newZealandPlayers = post?.teams?.newZealand?.players?.values?.map {
            SquadData(it, post.teams.newZealand.nameShort)
        } ?: emptyList()

        allPlayers = indiaPlayers + newZealandPlayers
        teamIndiaPlayers = indiaPlayers
        teamNewZealandPlayers = newZealandPlayers

        playersAdapter = PlayersAdapter(requireActivity(), allPlayers)
        binding.recyclerViewPlayers.adapter = playersAdapter

        binding.recyclerViewPlayers.layoutManager = LinearLayoutManager(requireContext())

        binding.chipGroupPlayers.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_all_squad -> {
                    playersAdapter = PlayersAdapter(requireActivity(), allPlayers)
                }

                R.id.chip_team_one -> {
                    playersAdapter = PlayersAdapter(requireActivity(), teamIndiaPlayers)
                }

                R.id.chip_team_two -> {
                    playersAdapter = PlayersAdapter(requireActivity(), teamNewZealandPlayers)
                }
            }
            binding.recyclerViewPlayers.adapter = playersAdapter
        }
    }
}