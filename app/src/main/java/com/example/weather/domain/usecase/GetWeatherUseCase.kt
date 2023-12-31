package com.example.weather.domain.usecase

import com.example.weather.common.DataState
import com.example.weather.data.model.CurrentWeatherResponse
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(hashmap: HashMap<String, String>): Flow<DataState<CurrentWeatherResponse>> =
        flow {
            try {
                emit(DataState.Loading())
                val weather = repository.getWeather(hashmap)
                emit(DataState.Success(weather))
            } catch (e: Exception) {
                emit(DataState.Error("UnExpected Error. " + e.localizedMessage))
            }
        }
}