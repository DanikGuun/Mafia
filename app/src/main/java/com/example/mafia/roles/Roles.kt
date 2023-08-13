package com.example.mafia.roles

class Roles {
    class Peaceful: Role{
        override val name = "Мирный житель"
            get() = field

        override val description = "Цель - выжить и помочь раскрыть членов мафии"
            get() = field

        override fun action() {

        }

    }
}