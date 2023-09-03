package com.example.mafia.roles

import java.io.Serializable

interface Role: Serializable {
    val name: String
    val description: String
    val icon: Int
    fun action()
}