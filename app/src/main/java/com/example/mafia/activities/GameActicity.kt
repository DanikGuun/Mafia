package com.example.mafia.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mafia.R
import com.example.mafia.RolesData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameActicity: AppCompatActivity() {
    var rolesData: RolesData? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        rolesData = savedInstanceState?.getSerializable("rolesData", RolesData::class.java)
        blackToTransparrentAnim(findViewById(R.id.fadeConstraint))
        findViewById<Button>(R.id.game_start_button).setOnClickListener{nextRound(findViewById(R.id.fadeConstraint), "мафия")}
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun nextRound(view: View, role: String){
        findViewById<TextView>(R.id.fadeText).text = "Просыпается $role"
        transparentToBlackAnim(view)
        GlobalScope.launch {
            delay(2500)
            blackToTransparrentAnim(view)
        }

    }
    private fun blackToTransparrentAnim(view: View){
        val animator = AlphaAnimation(1f, 0f)
        animator.fillAfter = true
        animator.duration = 2000
        view.startAnimation(animator)
    }

    private fun transparentToBlackAnim(view: View){
        val animator = AlphaAnimation(0f, 1f)
        animator.fillAfter = true
        animator.duration = 2000
        view.startAnimation(animator)
    }
}