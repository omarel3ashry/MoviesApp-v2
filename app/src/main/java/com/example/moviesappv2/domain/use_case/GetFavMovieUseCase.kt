package com.example.moviesappv2.domain.use_case

import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
class GetFavMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(id: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.loading(null))
//        try {
//            val favMovie = movieRepository.getFavMovieById(id).toMovie()
//            emit(Resource.success(favMovie))
//        } catch (e: Exception) {
//            emit(Resource.error(null, e.localizedMessage ?: "An unexpected error occurred"))
//        }
    }
}