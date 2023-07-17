package com.example.moviesappv2.presentation.movie_detail

import android.annotation.SuppressLint
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
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.presentation.home.MovieItemListener
import com.example.moviesappv2.presentation.home.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var movieGenresAdapter: MovieGenresAdapter
    private lateinit var similarMoviesAdapter: MoviesAdapter
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
                    populateSimilarMoviesRecView(resource.data!!.movies)
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
        viewModel.isFav.observe(viewLifecycleOwner) { isFav ->
            toggleFavBtn(isFav)
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

    private fun populateSimilarMoviesRecView(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            binding.similarMoviesTV.visibility = View.VISIBLE
            binding.similarMoviesRV.visibility = View.VISIBLE
            binding.similarMoviesRV.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            similarMoviesAdapter = MoviesAdapter(object : MovieItemListener {
                override fun onClick(item: Movie, position: Int) {
                }
            })
            binding.similarMoviesRV.adapter = similarMoviesAdapter
            similarMoviesAdapter.submitList(movies)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun toggleFavBtn(isFav: Boolean) {
        binding.favProgressBar.visibility = View.GONE
        binding.favToggleBtn.visibility = View.VISIBLE
        if (isFav) {
            binding.favTV.text = "Unfavorite"
            binding.favToggleBtn.isChecked = true
            binding.favToggleBtn.setOnClickListener {
                viewModel.removeMovieFromFav(movie)
            }
        } else {
            binding.favTV.text = "Favorite"
            binding.favToggleBtn.isChecked = false
            binding.favToggleBtn.setOnClickListener {
                viewModel.addMovieToFav(movie)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}