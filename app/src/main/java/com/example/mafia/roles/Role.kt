package com.example.mafia.roles

import java.io.Serializable

abstract class Role: Serializable {
    abstract val name: String
    abstract val description: String
    abstract val icon: Int
    abstract fun action()
    override fun equals(other: Any?): Boolean {
        if(other is Role){return other.name == this.name}
        return false
    }
}