package com.example.mafia.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.mafia.R
import com.example.mafia.RolesData
import com.example.mafia.roles.Role
import com.example.mafia.roles.Roles

class GameSettingsActivity: AppCompatActivity() {
    private var namesCounter = 0
        get() = field
        set(value) {
            findViewById<TextView>(R.id.roles_count).text = buildString {
                append("выбрано ")
                append(rolesPicked)
                append("/")
        append(namesCounter+1)
    }
            field = value
        }
    private var rolesPicked = 0
    private val players = ArrayList<String>()
    private val roles = ArrayList<Role>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start_activity)
        findViewById<Button>(R.id.player_add_button).setOnClickListener {onAddPlayerButton()}
        findViewById<Button>(R.id.start_button).setOnClickListener {onPlayButton()}
        generateRoles()
        addPlayer("asdf")
        addPlayer("asdf")
    }

    private fun onAddPlayerButton(){
        val alertLayout = layoutInflater.inflate(R.layout.add_player_alert, null)
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setView(alertLayout)
        val alert = alertBuilder.create()

        alertLayout.findViewById<Button>(R.id.accept_player)?.setOnClickListener {acceptPlayer(alert)}
        alertLayout.findViewById<Button>(R.id.cancel_player)?.setOnClickListener {cancelPlayer(alert)}
        alert.show()
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun generateRoles(){
        val baseIconID = 100
        val baseRoleNameID = 200
        val baseCountTextID = 300
        val baseCounterID = 400
        val basePlusID = 500
        val baseMinusID = 600
        var count = 0
        Roles.getAllRoles().forEach { role ->
            val mainLinearLayout = findViewById<LinearLayout>(R.id.game_settings_linear_layout)//ScrollView
            val roleConstraint = ConstraintLayout(this)//для роли
            roleConstraint.id = count
            roleConstraint.background = getDrawable(R.drawable.role_bar_shape)
            mainLinearLayout.addView(roleConstraint, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180))


            //картинка
            val icon = ImageView(this)
            icon.id = baseIconID + count
            icon.setImageDrawable(getDrawable(role.icon))
            roleConstraint.addView(icon, ViewGroup.LayoutParams(150, 150))
            //название
            val roleName = TextView(this)
            roleName.id = baseRoleNameID + count
            roleName.text = role.name
            roleName.setTextAppearance(R.style.PlayerNameText)
            roleName.textSize = 25f
            roleName.gravity = Gravity.CENTER_VERTICAL
            roleName.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            roleConstraint.addView(roleName, ViewGroup.LayoutParams(360, 150))
            //колво
            val counterText = TextView(this)
            counterText.id = baseCountTextID + count
            counterText.text = getString(R.string.count)
            counterText.setTextAppearance(R.style.roles_counter_text)
            roleConstraint.addView(counterText)
            //цифра
            val roleCounter = TextView(this)
            roleCounter.id = baseCounterID + count
            roleCounter.text = "0"
            roleCounter.setTextAppearance(R.style.roles_counter_text)
            roleConstraint.addView(roleCounter)
            //+
            val plusButton = TextView(this)
            plusButton.id = basePlusID + count
            plusButton.isClickable = true
            plusButton.stateListAnimator = Button(this).stateListAnimator
            plusButton.text = "+"
            plusButton.textSize = 24f
            plusButton.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            plusButton.setOnClickListener{onAddRole(roleCounter, role)}
            plusButton.setTextAppearance(R.style.roles_counter_text)
            plusButton.setTextColor(getColor(R.color.roles_plus_minus_black))
            roleConstraint.addView(plusButton, ViewGroup.LayoutParams(80, 80))
            //-
            val minusButton = TextView(this)
            minusButton.id = baseMinusID + count
            minusButton.isClickable = true
            minusButton.stateListAnimator = Button(this).stateListAnimator
            minusButton.text = "-"
            minusButton.textSize = 24f
            minusButton.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            minusButton.setOnClickListener{onMinusRole(roleCounter, role, roleCounter)}
            minusButton.setTextAppearance(R.style.roles_counter_text)
            minusButton.setTextColor(getColor(R.color.roles_plus_minus_black))
            roleConstraint.addView(minusButton, ViewGroup.LayoutParams(80, 80))

            //Привязка
            val constraintSet = ConstraintSet()
            constraintSet.clone(roleConstraint)

            //картинка
            constraintSet.connect(icon.id, ConstraintSet.LEFT, roleConstraint.id, ConstraintSet.LEFT)
            constraintSet.connect(icon.id, ConstraintSet.TOP, roleConstraint.id, ConstraintSet.TOP)
            constraintSet.connect(icon.id, ConstraintSet.BOTTOM, roleConstraint.id, ConstraintSet.BOTTOM)
            //название
            constraintSet.connect(roleName.id, ConstraintSet.LEFT, icon.id, ConstraintSet.RIGHT)
            constraintSet.connect(roleName.id, ConstraintSet.TOP, roleConstraint.id, ConstraintSet.TOP)
            constraintSet.connect(roleName.id, ConstraintSet.BOTTOM, roleConstraint.id, ConstraintSet.BOTTOM)
            //колво
            constraintSet.connect(counterText.id, ConstraintSet.TOP, roleConstraint.id, ConstraintSet.TOP)
            constraintSet.connect(counterText.id, ConstraintSet.RIGHT, roleConstraint.id, ConstraintSet.RIGHT, 120)
            //цифра
            constraintSet.connect(roleCounter.id, ConstraintSet.TOP, counterText.id, ConstraintSet.BOTTOM)
            constraintSet.connect(roleCounter.id, ConstraintSet.LEFT, counterText.id, ConstraintSet.LEFT)
            constraintSet.connect(roleCounter.id, ConstraintSet.RIGHT, counterText.id, ConstraintSet.RIGHT)
            //+
            constraintSet.connect(plusButton.id, ConstraintSet.TOP, counterText.id, ConstraintSet.BOTTOM)
            constraintSet.connect(plusButton.id, ConstraintSet.LEFT, roleCounter.id, ConstraintSet.RIGHT, 25)
            //-
            constraintSet.connect(minusButton.id, ConstraintSet.TOP, counterText.id, ConstraintSet.BOTTOM)
            constraintSet.connect(minusButton.id, ConstraintSet.RIGHT, roleCounter.id, ConstraintSet.LEFT, 25)

            constraintSet.applyTo(roleConstraint)

            count++
        }
    }
    @SuppressLint("SetTextI18n")
    private fun onAddRole(countText: TextView, role: Role){
        if (rolesPicked < namesCounter){
            //меняем счётчик на роли
            countText.text = (countText.text.toString().toInt() + 1).toString()
            roles.add(role)
            rolesPicked++
            //меняем общий счётчик
            val rolesCount = findViewById<TextView>(R.id.roles_count)
            val counters = rolesCount.text.toString().split(" ")[1].split("/").toIntList()//список сколько из скольки ролей есть
            counters[0]++
            rolesCount.text = "выбрано ${counters[0]}/${counters[1]}"
        }
        else{
            Toast.makeText(this, "Все роли уже выбраны", Toast.LENGTH_SHORT).show()
        }

    }
    @SuppressLint("SetTextI18n")
    private fun onMinusRole(countText: TextView, role: Role, roleCounter: TextView){
        if (rolesPicked > 0 && roleCounter.text.toString().toInt() > 0){
            //меняем счётчик на роли
            countText.text = (countText.text.toString().toInt() - 1).toString()
            roles.remove(role)
            rolesPicked--
            //меняем общий счётчик
            val rolesCount = findViewById<TextView>(R.id.roles_count)
            val counters = rolesCount.text.toString().split(" ")[1].split("/").toIntList() //список сколько из скольки ролей есть
            counters[0]--
            rolesCount.text = "выбрано ${counters[0]}/${counters[1]}"
        }
        else{
            Toast.makeText(this, "Ролей не может быть меньше 0", Toast.LENGTH_SHORT).show()
        }
    }
    private fun onPlayButton(){
        val data = RolesData()
        data.distributeRoles(players, roles)
        for (key in data.rolesMap.keys) {
            Log.d("TAG", "$key: ${data.rolesMap[key]}")
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addPlayer(name: String){
        val mainLinear = findViewById<LinearLayout>(R.id.game_settings_linear_layout)
        var playerName = name

        //добавляем плашку для имени
        val playerNamePlank = LinearLayout(this)
        playerNamePlank.background = getDrawable(R.drawable.role_bar_shape)
        mainLinear.addView(playerNamePlank, namesCounter, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180))

        //добавляем само имя
        val playerNameTextView = TextView(this)
        playerNameTextView.setTextAppearance(R.style.PlayerNameText)
        playerNameTextView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        playerNameTextView.gravity = Gravity.CENTER_VERTICAL
        playerNamePlank.addView(playerNameTextView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180))

        //если имена повторяются, то добавляем циферку в конец
        var notRepeatName = 1
        if (playerName in players){
            while (playerName+notRepeatName.toString() in players){
                notRepeatName++
            }
            playerName += notRepeatName.toString()
        }
        
        playerNameTextView.text = playerName
        players.add(playerName)

        namesCounter++
    }
    private fun acceptPlayer(alert: AlertDialog){
        addPlayer(alert.findViewById<EditText>(R.id.enter_player_name)?.text.toString())
        alert.cancel()
    }
    private fun cancelPlayer(alert: AlertDialog){
        alert.cancel()
    }
}
fun List<String>.toIntList(): ArrayList<Int>{
    val intList = ArrayList<Int>()
    for (i in this) intList.add(i.toInt())
    return intList
}