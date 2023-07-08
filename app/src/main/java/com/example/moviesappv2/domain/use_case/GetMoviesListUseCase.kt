package com.example.moviesappv2.domain.use_case

import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.data.remote.dto.toMoviesList
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
class GetMoviesListUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(listType: MoviesListType): Flow<Resource<MoviesList>> = flow {
        emit(Resource.loading(null))
        try {
            val moviesList = movieRepository.getMoviesList(listType).toMoviesList()
            emit(Resource.success(moviesList))
        } catch (e: HttpException) {
            emit(Resource.error(null, e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.error(null, "Couldn't reach server. Check your internet connection."))
        }
    }
}