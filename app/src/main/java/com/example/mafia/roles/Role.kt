package com.example.mafia.roles

interface Role {
    val name: String
    val description: String
    fun action()
}