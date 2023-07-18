package com.example.moviesappv2.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesappv2.R
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.databinding.FragmentHomeBinding
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sharedHomeViewModel: HomeViewModel by activityViewModels()

    private lateinit var popularMoviesAdapter: PosterMoviesAdapter
    private lateinit var nowPlayingMoviesAdapter: BackdropMoviesAdapter
    private lateinit var upcomingMoviesAdapter: BackdropMoviesAdapter
    private lateinit var topRatedMoviesAdapter: MoviesAdapter

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
        popularRecViewSetup()
        nowPlayingRecViewSetup()
        upcomingRecViewSetup()
        topRatedRecViewSetup()
        setupViewModelObservers()
        binding.apply {
            morePopular.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.POPULAR)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }
            moreNowPlaying.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.NOW_PLAYING)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }
            moreUpcoming.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.UPCOMING)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }
            moreTopRated.setOnClickListener {
                sharedHomeViewModel.setMoviesListType(MoviesListType.TOP_RATED)
                findNavController().navigate(R.id.action_homeFragment_to_moviesListFragment)
            }

        }
    }

    private fun setupViewModelObservers() {
        sharedHomeViewModel.popularMoviesList.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    popularMoviesAdapter.submitList(resource.data!!.movies)
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
                    nowPlayingMoviesAdapter.submitList(resource.data!!.movies)
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
                    upcomingMoviesAdapter.submitList(resource.data!!.movies)
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
                    topRatedMoviesAdapter.submitList(resource.data!!.movies.take(10))
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d("MainActivity", "onCreate: ${resource.message}")
                }
            }
        }
    }

    private fun popularRecViewSetup() {
        binding.popularRecView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        popularMoviesAdapter = PosterMoviesAdapter(object : PosterItemListener {
            override fun onClick(item: Movie, position: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item.id)
                findNavController().navigate(action)
            }
        })
        binding.popularRecView.adapter = popularMoviesAdapter
    }

    private fun nowPlayingRecViewSetup() {
        binding.nowPlayingRecView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        nowPlayingMoviesAdapter = BackdropMoviesAdapter(object : BackdropItemListener {
            override fun onClick(item: Movie, position: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item.id)
                findNavController().navigate(action)
            }
        })
        binding.nowPlayingRecView.adapter = nowPlayingMoviesAdapter
    }

    private fun upcomingRecViewSetup() {
        binding.upcomingRecView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        upcomingMoviesAdapter = BackdropMoviesAdapter(object : BackdropItemListener {
            override fun onClick(item: Movie, position: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item.id)
                findNavController().navigate(action)
            }
        })
        binding.upcomingRecView.adapter = upcomingMoviesAdapter
    }

    private fun topRatedRecViewSetup() {
        binding.topRatedRecView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        topRatedMoviesAdapter = MoviesAdapter(object : MovieItemListener {
            override fun onClick(item: Movie, position: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item.id)
                findNavController().navigate(action)
            }
        })
        binding.topRatedRecView.adapter = topRatedMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}