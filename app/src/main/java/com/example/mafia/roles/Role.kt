package com.example.mafia.roles

import android.os.Parcelable
import com.example.mafia.Player
import java.io.Serializable

abstract class Role: Serializable {
    abstract val name: String
    abstract val description: String
    abstract val icon: Int
    abstract fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>)
    override fun equals(other: Any?): Boolean {
        if(other is Role){return other.name == this.name}
        return false
    }
}