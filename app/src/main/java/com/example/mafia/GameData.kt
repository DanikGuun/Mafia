package com.example.mafia

import android.util.Log
import com.example.mafia.roles.Role
import java.io.Serializable
import kotlin.random.Random

class GameData(val playersList: ArrayList<Player>): Serializable {
    constructor() : this(ArrayList<Player>())

    val steps = HashMap<Role, ArrayList<ArrayList<Player>>>() //по роли определяется кто ходил, далее вложенный массив, где 0 - кто ходил, 1 - на кого ходили

    fun distributeRoles(names: ArrayList<String>, roles: ArrayList<Role>){
        val namesLength = names.lastIndex
        for(i in 0..namesLength){

            val randID: Int = Random.nextInt(0, roles.lastIndex+1)
            val player = Player(names[0], roles[randID]);
            playersList.add(player)

            names.remove(names[0])
            roles.remove(roles[randID])
        }
    }

    fun getPlayersWithRole(role: Role): ArrayList<Player>{
        val playersList = ArrayList<Player>()
        for(player in this.playersList){
            if(player.role == role) playersList.add(player)
        }
        return playersList
    }

    fun sumUpNight(): String{
        val str = StringBuilder()
        for(step in steps.keys){
            str.append(steps[step]!![0][0].role.action(this) + "\n\n")
        }
        return str.toString()
    }
}