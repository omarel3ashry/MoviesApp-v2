package com.example.moviesappv2.presentation.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.databinding.FragmentMoviesListBinding
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {


    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val sharedHomeViewModel: HomeViewModel by activityViewModels()
    private lateinit var currentMoviesList: MoviesList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        sharedHomeViewModel.moviesListType.observe(viewLifecycleOwner) { type ->
            when (type!!) {
                MoviesListType.POPULAR -> {
                    currentMoviesList = sharedHomeViewModel.popularMoviesList.value!!.data!!
                    binding.txtView.text = currentMoviesList.movies[0].title
                }

                MoviesListType.NOW_PLAYING -> {
                    currentMoviesList = sharedHomeViewModel.nowPlayingMoviesList.value!!.data!!
                    binding.txtView.text = currentMoviesList.movies[0].title
                }

                MoviesListType.UPCOMING -> {
                    currentMoviesList = sharedHomeViewModel.upcomingMoviesList.value!!.data!!
                    binding.txtView.text = currentMoviesList.movies[0].title
                }

                MoviesListType.TOP_RATED -> {
                    currentMoviesList = sharedHomeViewModel.topRatedMoviesList.value!!.data!!
                    binding.txtView.text = currentMoviesList.movies[0].title
                }
            }
        }
    }
}