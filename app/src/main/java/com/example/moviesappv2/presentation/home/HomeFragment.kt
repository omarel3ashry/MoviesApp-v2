package com.example.moviesappv2.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesappv2.R
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.databinding.FragmentHomeBinding
import com.example.moviesappv2.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sharedHomeViewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            popularMoviesListBtn.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.POPULAR)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }
            nowPlayingMoviesListBtn.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.NOW_PLAYING)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }
            upcomingMoviesListBtn.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.UPCOMING)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }
            topRatedMoviesListBtn.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.TOP_RATED)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }

        }

        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        sharedHomeViewModel.popularMoviesList.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success, see the log!", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d("HomeFragment", "setupViewModelObservers: ${resource.message}")
                }
            }
        }
        sharedHomeViewModel.nowPlayingMoviesList.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success, see the log!", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d("MainActivity", "onCreate: ${resource.message}")
                }
            }
        }
        sharedHomeViewModel.upcomingMoviesList.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success, see the log!", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d("MainActivity", "onCreate: ${resource.message}")
                }
            }
        }
        sharedHomeViewModel.topRatedMoviesList.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success, see the log!", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d("MainActivity", "onCreate: ${resource.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}