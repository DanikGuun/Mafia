package com.example.mafia

import android.util.Log
import com.example.mafia.roles.Role
import java.io.Serializable
import kotlin.random.Random

class GameData(val rolesMap: HashMap<String, Role>): Serializable {
    constructor() : this(HashMap<String, Role>())

    val steps = HashMap<Role, ArrayList<ArrayList<String>>>() //по роли определяется кто ходил, далее вложенный массив, где 0 - кто ходил, 1 - на кого ходили
    fun distributeRoles(names: ArrayList<String>, roles: ArrayList<Role>){
        val namesLength = names.lastIndex
        for(i in 0..namesLength){
            val randID: Int = Random.nextInt(0, roles.lastIndex+1)
            rolesMap[names[0]] = roles[randID]
            names.remove(names[0])
            roles.remove(roles[randID])
        }
    }

    fun getPlayersWithRole(role: Role): ArrayList<String>{
        val namesList = ArrayList<String>()
        for(name in rolesMap.keys){
            if(rolesMap[name] == role) namesList.add(name)
        }
        return namesList
    }

}