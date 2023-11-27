package com.example.mafia

import com.example.mafia.roles.Role
import java.io.Serializable

class Player(val name: String, val role: Role): Serializable {
    override fun equals(other: Any?): Boolean {
        if(other is Player) return this.name == other.name
        return false
    }

    override fun toString(): String {
        return "${name} (${role.toString()})"
    }
}