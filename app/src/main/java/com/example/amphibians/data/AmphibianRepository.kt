package com.example.amphibians.data

import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApiService

interface IAmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class AmphibianRepository(private val amphibianApiService: AmphibianApiService) :
    IAmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.getAmphibians()
    }

}