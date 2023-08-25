package com.example.mafia.roles

interface Role {
    val name: String
    val description: String
    val icon: Int
    fun action()
}