package com.example.marvelcomicsviewer.shared.characters.domain

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)