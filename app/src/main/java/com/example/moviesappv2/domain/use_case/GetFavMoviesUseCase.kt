package com.example.moviesappv2.domain.use_case

import androidx.lifecycle.LiveData
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.data.local.entities.MovieEntity
import com.example.moviesappv2.data.local.entities.toMovie
import com.example.moviesappv2.data.remote.dto.toMovie
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
class GetFavMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<List<Movie>> = flow {
        movieRepository.getFavMovies().flowOn(Dispatchers.IO).collect { entities ->
            val movies = entities.map { it.toMovie() }
            emit(movies)
        }
    }

}