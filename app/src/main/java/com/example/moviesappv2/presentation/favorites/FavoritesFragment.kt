package com.example.moviesappv2.presentation.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesappv2.databinding.FragmentFavoritesBinding
import com.example.moviesappv2.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favMoviesAdapter: FavMoviesAdapter
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteRecViewSetup()
        favoritesViewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                Log.d("FavoritesFragment", "${movies.size}")
                favMoviesAdapter.submitList(movies)
                for (movie in movies) {
                    Log.d("FavoritesFragment", "id = ${movie.id}")
                }
            } else Log.d("FavoritesFragment", "it is null bro!")
        }

    }

    private fun favoriteRecViewSetup() {
        binding.favMoviesRV.layoutManager = GridLayoutManager(requireContext(), 2)
        favMoviesAdapter = FavMoviesAdapter(object : FavMovieItemListener {
            override fun onClick(item: Movie, position: Int) {
                val action =
                    FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailFragment3(item)
                findNavController().navigate(action)
            }
        })
        binding.favMoviesRV.adapter = favMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}