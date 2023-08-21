package com.example.mafia

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class GameSettingsActivity: AppCompatActivity() {
    private var namesCounter = 0
    private val players = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start_activity)
        findViewById<Button>(R.id.player_add_button).setOnClickListener {onAddPlayerButton()}
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