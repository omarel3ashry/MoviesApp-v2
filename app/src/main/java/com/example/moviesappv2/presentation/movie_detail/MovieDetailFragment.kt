package com.example.moviesappv2.presentation.movie_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.databinding.FragmentMovieDetailBinding
import com.example.moviesappv2.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var movieGenresAdapter: MovieGenresAdapter
    private lateinit var movie: Movie

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
        // just for testing, will make it toggle next.
        binding.favTV.setOnClickListener {
            viewModel.addMovieToFav(movie)
        }

    }

    private fun setupViewModelObservers() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    movie = resource.data!!
                    bindMovieData(resource.data)
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
        viewModel.favMovie.observe(viewLifecycleOwner) { favMovie ->
            movie = favMovie
            bindMovieData(favMovie)
        }
        viewModel.favMovieId.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "id = ${resource.data}", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.LOADING -> {}
                Resource.Status.ERROR -> {
                    Log.d("MovieDetailFragment", "${resource.message}")
                }
            }
        }
    }

    private fun bindMovieData(movie: Movie) {
        Glide.with(requireContext())
            .load(movie.backdropPath)
            .into(binding.backdropImgView)
        binding.apply {
            titleTV.text = movie.title
            overviewTV.text = movie.overview
            rateTV.text = movie.voteAverage.toString()
        }
        populateGenreRecView(movie.genres)
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