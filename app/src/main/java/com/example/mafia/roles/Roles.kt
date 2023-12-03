package com.example.mafia.roles

import com.example.mafia.GameData
import com.example.mafia.Player
import com.example.mafia.R
import java.io.Serializable

object Roles: Serializable {
    const val ROLES_COUNT = 6
    fun getAllRoles(): Array<Role>{
        return arrayOf(Peaceful(), Mafia(), Doctor(), Sherif(), Prostitute(), Avenger(), Journalist())
    }
    fun getRolesQueue(): Array<Role>{
        return arrayOf(Prostitute(), Mafia(), Avenger(), Doctor(), Journalist(), Sherif())
    }
    class Peaceful: Role(), Serializable{
        override val name = "Мирный"
        override val isEvil = false
        override val description = "Цель - выжить и помочь раскрыть членов мафии"
        override val icon = R.drawable.peace
        override fun action(gameData: GameData): String {
            return ""
        }

    }
    class Mafia: Role(), Serializable{
        override val name = "Мафия"
        override val isEvil = true
        override val description = "Цель - убить всех мирных"
        override val icon = R.drawable.mafia
        override fun action(gameData: GameData):String {
            if(gameData.steps[Mafia()]!![1][0] != gameData.steps[Doctor()]!![1][0]){
                gameData.playersList.remove(gameData.steps[Mafia()]!![1][0]);
            }
            return "Мафия  убила ${gameData.steps[Mafia()]!![1][0].name}"
        }
    }
    class Doctor: Role(), Serializable{
        override val name = "Доктор"
        override val isEvil = false
        override val description = "Лечит"
        override val icon = R.drawable.doctor
        override fun action(gameData: GameData): String {
            return "Доктор вылечил ${gameData.steps[Doctor()]!![1][0].name}"
        }
    }
    class Sherif: Role(), Serializable{
        override val name = "Комиссар"
        override val isEvil = false
        override val description = "Ищет Мафию"
        override val icon = R.drawable.sherif
        override fun action(gameData: GameData): String {
            if(gameData.steps[Sherif()]!![1][0].role.isEvil == true){
                return "У комиссара правильные догадки (${gameData.steps[Sherif()]!![1][0].name})"
            }
            return "У комиссара неправильные догадки (${gameData.steps[Sherif()]!![1][0].name})"
        }
    }
    class Prostitute: Role(), Serializable{
        override val name = "Путана"
        override val isEvil = false
        override val description = "чпокается"
        override val icon = R.drawable.putana
        override fun action(gameData: GameData): String{
            return "Проститутка пришла к ${gameData.steps[Prostitute()]!![1][0].name}"
        }
    }
    class Avenger: Role(), Serializable{
        override val name = "Мститель"
        override val isEvil = true
        override val description = "Убивает"
        override val icon = R.drawable.avenger
        override fun action(gameData: GameData): String {
            if(gameData.steps[Avenger()]!![1][0] != gameData.steps[Doctor()]!![1][0]){
                gameData.playersList.remove(gameData.steps[Avenger()]!![1][0])
            }
            return "Мститель убил ${gameData.steps[Avenger()]!![1][0].name}"
        }
    }
    class Journalist: Role(), Serializable{
        override val name = "Журналист"
        override val isEvil = false
        override val description = "Смотрит в одной ли команде игроки"
        override val icon = R.drawable.journaler
        override fun action(gameData: GameData): String {
            if(gameData.steps[Journalist()]!![1][0].role.isEvil == gameData.steps[Journalist()]!![1][1].role.isEvil){
                return "${gameData.steps[Journalist()]!![1][0].name} и ${gameData.steps[Journalist()]!![1][1].name} играют в одной команде"
            }
            return "${gameData.steps[Journalist()]!![1][0].name} и ${gameData.steps[Journalist()]!![1][1].name} играют в разных командах"
        }
    }
    class Kamikaze: Role(), Serializable{
        //будет попозже
        override val name = "Камикадзе"
        override val isEvil = true
        override val description = "Убивает на голосовании"
        override val icon = R.drawable.kamikadze
        override fun action(gameData: GameData): String {
            return ""
        }
    }
}