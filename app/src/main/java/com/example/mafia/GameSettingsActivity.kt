package com.example.mafia

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class GameSettingsActivity: AppCompatActivity() {
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

        alertLayout.findViewById<Button>(R.id.accept_player)?.setOnClickListener {addPlayer(alert)}
        alertLayout.findViewById<Button>(R.id.cancel_player)?.setOnClickListener {cancelPlayer(alert)}
        alert.show()
    }
    private fun addPlayer(alert: AlertDialog){
        Log.d("TAG", "addPlayer: 123")
        alert.cancel()
    }
    private fun cancelPlayer(alert: AlertDialog){
        alert.cancel()
    }
}