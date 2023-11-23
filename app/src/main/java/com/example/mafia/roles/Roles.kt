package com.example.mafia.roles

import com.example.mafia.Player
import com.example.mafia.R
import java.io.Serializable

object Roles: Serializable {
    const val ROLES_COUNT = 7
    fun getAllRoles(): Array<Role>{
        return arrayOf(Peaceful(), Mafia(), Doctor(), Sherif(), Prostitute(), Avenger(), Journalist(), Kamikaze())
    }
    fun getRolesQueue(): Array<Role>{
        return arrayOf(Prostitute(), Mafia(), Avenger(), Doctor(), Journalist(), Kamikaze(), Sherif())
    }
    class Peaceful: Role(), Serializable{
        override val name = "Мирный"
        override val description = "Цель - выжить и помочь раскрыть членов мафии"
        override val icon = R.drawable.peace
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {

        }

    }
    class Mafia: Role(), Serializable{
        override val name = "Мафия"
        override val description = "Цель - убить всех мирных"
        override val icon = R.drawable.mafia
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {
        }
    }
    class Doctor: Role(), Serializable{
        override val name = "Доктор"
        override val description = "Лечит"
        override val icon = R.drawable.doctor
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {
        }
    }
    class Sherif: Role(), Serializable{
        override val name = "Комиссар"
        override val description = "Ищет Мафию"
        override val icon = R.drawable.sherif
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {
        }
    }
    class Prostitute: Role(), Serializable{
        override val name = "Путана"
        override val description = "чпокается"
        override val icon = R.drawable.putana
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {
            TODO("Not yet implemented")
        }
    }
    class Avenger: Role(), Serializable{
        override val name = "Мститель"
        override val description = "Убивает"
        override val icon = R.drawable.avenger
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {

        }
    }
    class Journalist: Role(), Serializable{
        override val name = "Журналист"
        override val description = "Смотрит в одной ли команде игроки"
        override val icon = R.drawable.journaler
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {
        }
    }
    class Kamikaze: Role(), Serializable{
        override val name = "Камикадзе"
        override val description = "Убивает на голосовании"
        override val icon = R.drawable.kamikadze
        override fun action(steps: HashMap<Role, ArrayList<ArrayList<Player>>>) {
        }
    }
}