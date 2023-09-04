package com.example.mafia.roles

import com.example.mafia.R
import java.io.Serializable

object Roles: Serializable {
    fun getAllRoles(): Array<Role>{
        return arrayOf(Peaceful(), Mafia(), Doctor(), Sherif(), Prostitute(), Avenger(), Journalist(), Kamikaze())
    }
    class Peaceful: Role(), Serializable{
        override val name = "Мирный"
            get() = field

        override val description = "Цель - выжить и помочь раскрыть членов мафии"
            get() = field
        override val icon = R.drawable.peace
            get() = field
        override fun action() {

        }

    }
    class Mafia: Role(), Serializable{
        override val name = "Мафия"
            get() = field
        override val description = "Цель - убить всех мирных"
            get() = field
        override val icon = R.drawable.mafia
            get() = field
        override fun action() {
        }
    }
    class Doctor: Role(), Serializable{
        override val name = "Доктор"
            get() = field
        override val description = "Лечит"
            get() = field
        override val icon = R.drawable.doctor
            get() = field
        override fun action() {
        }
    }
    class Sherif: Role(), Serializable{
        override val name = "Комиссар"
            get() = field
        override val description = "Ищет Мафию"
            get() = field
        override val icon = R.drawable.sherif
            get() = field
        override fun action() {
        }
    }
    class Prostitute: Role(), Serializable{
        override val name = "Путана"
            get() = field
        override val description = "чпокается"
            get() = field
        override val icon = R.drawable.putana
            get() = field
        override fun action() {
            TODO("Not yet implemented")
        }
    }
    class Avenger: Role(), Serializable{
        override val name = "Мститель"
            get() = field
        override val description = "Убивает"
            get() = field
        override val icon = R.drawable.avenger
            get() = field
        override fun action() {

        }
    }
    class Journalist: Role(), Serializable{
        override val name = "Журналист"
            get() = field
        override val description = "Смотрит в одной ли команде игроки"
            get() = field
        override val icon = R.drawable.journaler
            get() = field
        override fun action() {
        }
    }
    class Kamikaze: Role(), Serializable{
        override val name = "Камикадзе"
            get() = field
        override val description = "Убивает на голосовании"
            get() = field
        override val icon = R.drawable.kamikadze
            get() = field
        override fun action() {
        }
    }
}