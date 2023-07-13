package com.example.moviesappv2.presentation.movie_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var movieGenresAdapter: MovieGenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Glide.with(requireContext())
                        .load(resource.data!!.backdropPath)
                        .into(binding.backdropImgView)
                    binding.apply {
                        titleTV.text = resource.data.title
                        overviewTV.text = resource.data.overview
                        rateTV.text = resource.data.voteAverage.toString()
                    }
                    populateGenreRecView(resource.data.genres)
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d(
                        "MovieDetailFragment",
                        "setupViewModelObservers: ${resource.message}"
                    )
                }
            }
        }
        viewModel.similarMovies.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {

                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d(
                        "MovieDetailFragment",
                        "setupViewModelObservers: ${resource.message}"
                    )
                }
            }

        }
    }

    private fun populateGenreRecView(genres: List<String>) {
        binding.genreRV.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        movieGenresAdapter = MovieGenresAdapter()
        binding.genreRV.adapter = movieGenresAdapter
        movieGenresAdapter.submitList(genres)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}