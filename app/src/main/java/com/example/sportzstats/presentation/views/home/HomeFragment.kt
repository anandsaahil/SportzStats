package com.example.sportzstats.presentation.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sportzstats.R
import com.example.sportzstats.databinding.FragmentHomeBinding
import com.example.sportzstats.domain.model.Post
import com.example.sportzstats.presentation.utils.DateFormatter
import com.example.sportzstats.presentation.utils.Helper
import com.example.sportzstats.presentation.utils.Team
import com.example.sportzstats.presentation.viewmodels.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val postViewModel: PostViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initActions()
    }

    private fun initObservers() {
        binding.layoutLoader.visibility = View.VISIBLE
        postViewModel.posts.observe(viewLifecycleOwner, Observer { post ->
            initViews()
        })
        postViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })

        postViewModel.callNextApi()  // Calls the API (alternates based on the counter)
    }

    private fun initActions() {
        binding.animStartBtn.setOnClickListener {
            navigateToDetails(postViewModel.posts.value)
        }
    }

    private fun initViews() {
        mapFlag()
        binding.layoutLoader.visibility = View.GONE
        binding.animLive.visibility =
            if (postViewModel.posts.value?.matchDetail?.match?.liveCoverage.equals(Helper.Yes.name,true))
                View.VISIBLE else View.GONE
        binding.tvMatchType.text = postViewModel.posts.value?.matchDetail?.series?.name
        binding.tvTeamOne.text = postViewModel.posts.value?.teams?.teamOne?.nameShort
        binding.tvTeamTwo.text = postViewModel.posts.value?.teams?.teamTwo?.nameShort
        val formattedDateTime = DateFormatter.formatDateTime(
            postViewModel.posts.value?.matchDetail?.match?.date,
            postViewModel.posts.value?.matchDetail?.match?.time
        )
        binding.tvMatchTime.text = formattedDateTime
        binding.tvMatchVenue.text = postViewModel.posts.value?.matchDetail?.venue?.name
        binding.animStartBtn.isClickable = true
    }

    private fun mapFlag(){
        val teamOneFlags = mapOf(
            Team.IND.name to R.drawable.ic_flag_india,
            Team.PAK.name to R.drawable.ic_flag_pak
        )

        val teamTwoFlags = mapOf(
            Team.NZ.name to R.drawable.ic_flag_new_zealand,
            Team.SA.name to R.drawable.ic_flag_sa
        )

        val teamOne = postViewModel.posts.value?.teams?.teamOne?.nameShort
        val teamTwo = postViewModel.posts.value?.teams?.teamTwo?.nameShort

        binding.ivFlagOne.setImageResource(teamOneFlags[teamOne] ?: R.mipmap.ic_launcher)
        binding.ivFlagTwo.setImageResource(teamTwoFlags[teamTwo] ?: R.mipmap.ic_launcher)
    }

    private fun navigateToDetails(post: Post?) {
        val action = post?.let { HomeFragmentDirections.actionHomeToDetails(it) }
        action?.let { findNavController().navigate(it) }
    }
}
