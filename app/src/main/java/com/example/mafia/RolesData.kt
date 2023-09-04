package com.example.mafia

import android.util.Log
import com.example.mafia.roles.Role
import java.io.Serializable
import kotlin.random.Random

class RolesData(val rolesMap: HashMap<String, Role>): Serializable {
    constructor() : this(HashMap<String, Role>())

    fun distributeRoles(names: ArrayList<String>, roles: ArrayList<Role>){
        val namesLength = names.lastIndex
        for(i in 0..namesLength){
            val randID: Int
            randID = Random.nextInt(0, roles.lastIndex+1)
            rolesMap[names[0]] = roles[randID]
            names.remove(names[0])
            roles.remove(roles[randID])
        }
    }
}