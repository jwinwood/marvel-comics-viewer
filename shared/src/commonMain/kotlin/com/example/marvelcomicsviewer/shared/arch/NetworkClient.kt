package com.example.marvelcomicsviewer.shared.arch

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.json.serializer.KotlinxSerializer.Companion.DefaultJson
import kotlinx.serialization.json.Json

private val JsonConfig: Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = false
}

val httpClient = HttpClient {
    expectSuccess = false
    install(JsonFeature) {
        serializer = KotlinxSerializer(json = JsonConfig)
    }
}