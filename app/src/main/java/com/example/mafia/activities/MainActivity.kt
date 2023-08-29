package com.example.mafia.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mafia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //биндинг кнопок
        findViewById<Button>(R.id.role_button).setOnClickListener { onRolesButtonPressed() }
        findViewById<Button>(R.id.play_button).setOnClickListener {onPlayButtonPressed()}
    }
    //переход на активити с ролями
    private fun onRolesButtonPressed(){
        val intent = Intent(this, RolesActivity::class.java)
        startActivity(intent)
    }
    private fun onPlayButtonPressed(){
        val intent = Intent(this, GameSettingsActivity::class.java)
        startActivity(intent)
    }
}