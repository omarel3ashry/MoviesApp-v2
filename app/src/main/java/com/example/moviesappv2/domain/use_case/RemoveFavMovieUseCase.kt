package com.example.moviesappv2.domain.use_case

import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.model.toMovieEntity
import com.example.moviesappv2.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
class RemoveFavMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movie: Movie): Flow<Resource<Boolean>> = flow {
        emit(Resource.loading(false))
        try {
            movieRepository.removeFavMovie(movie.toMovieEntity())
            emit(Resource.success(true))
        } catch (e: Exception) {
            emit(Resource.error(false, e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}