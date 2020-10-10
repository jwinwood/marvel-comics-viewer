package com.example.marvelcomicsviewer.shared.characters.data.api

import com.example.marvelcomicsviewer.shared.arch.DataStore
import com.example.marvelcomicsviewer.shared.characters.domain.Character
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.Url
import kotlinx.coroutines.flow.*

class CharacterNetworkDataStore(
    private val httpClient: HttpClient
) : DataStore<CharacterDataWrapper> {

//    override suspend fun fetch(id: Int): Flow<Character?> =
//        makeNetworkCall<CharacterDataWrapper>(buildUrl(id))
//                .mapNotNull { it.data.results }
//                .first()
//                .map { transformToCharacter(it) }
//                .asFlow()

    override suspend fun fetch():Flow<CharacterDataWrapper> =
        makeNetworkCall(buildUrl())

    private suspend inline fun <reified T> makeNetworkCall(url: Url): Flow<T> = flow {
        emit(httpClient.get(url))
    }

    companion object {
        // https://gateway.marvel.com:443/v1/public/characters?apikey=
        private const val host = "gateway.marvel.com"
        private const val path = "/v1/public/characters"
        private const val apiKey = "e92bc5fc949f78c6f03912a621d42c4e"
        private const val hash = "841015f57c2a706ed01266de3e4c44fc"
        private const val timestamp = "1601581702"

        fun buildUrl(): Url {
            val parametersBuilder = ParametersBuilder(1)
            parametersBuilder.append("apikey", apiKey)
            parametersBuilder.append("hash", hash)
            parametersBuilder.append("ts", timestamp)

            return URLBuilder(
                protocol = URLProtocol.HTTPS,
                host = host,
                encodedPath = path,
                parameters = parametersBuilder
            ).build()
        }
    }
}