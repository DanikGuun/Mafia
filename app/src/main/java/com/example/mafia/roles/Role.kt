package com.example.mafia.roles

import android.graphics.drawable.Drawable

interface Role {
    val name: String
    val description: String
    val icon: Int
    fun action()
}