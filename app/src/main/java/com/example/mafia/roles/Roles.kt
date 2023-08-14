package com.example.mafia.roles

object Roles {
    fun getAllRoles(): Array<Role>{
        return arrayOf(Peaceful(), Mafia(), Doctor(), Sherif(), Prostitute(), Avenger(), Journalist(), Kamikaze())
    }
    class Peaceful: Role{
        override val name = "Мирный житель"
            get() = field

        override val description = "Цель - выжить и помочь раскрыть членов мафии"
            get() = field

        override fun action() {

        }

    }
    class Mafia: Role{
        override val name = "Мафия"
            get() = field
        override val description = "Цель - убить всех мирных"
            get() = field

        override fun action() {
        }
    }
    class Doctor: Role{
        override val name = "Доктор"
            get() = field
        override val description = "Лечит"
            get() = field

        override fun action() {
        }
    }
    class Sherif: Role{
        override val name = "Комиссар"
            get() = field
        override val description = "Ищет Мафию"
            get() = field

        override fun action() {
        }
    }
    class Prostitute: Role {
        override val name = "Путана"
            get() = field
        override val description = "чпокается"
            get() = field

        override fun action() {
            TODO("Not yet implemented")
        }
    }
    class Avenger: Role{
        override val name = "Мститель"
            get() = field
        override val description = "Убивает"
            get() = field

        override fun action() {

        }
    }
    class Journalist: Role{
        override val name = "Журналист"
            get() = field
        override val description = "Смотрит в одной ли команде игроки"
            get() = field

        override fun action() {
        }
    }
    class Kamikaze: Role{
        override val name = "Камикадзе"
            get() = field
        override val description = "Убивает на голосовании"
            get() = field

        override fun action() {
        }
    }
}