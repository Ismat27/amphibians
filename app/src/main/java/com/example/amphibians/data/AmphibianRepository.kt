package com.example.amphibians.data

import android.util.Log
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApiService

interface IAmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class AmphibianRepository(private val amphibianApiService: AmphibianApiService) :
    IAmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        try {
            return amphibianApiService.getAmphibians()
        } catch (e: Exception) {
            Log.d("AmphibianRepository", e.message ?: "AmphibianRepository error")
            return LocalDataSource.amphibians
        }

    }

}