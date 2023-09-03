package com.example.mafia.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mafia.R
import com.example.mafia.RolesData
import com.example.mafia.roles.Role

class GameActicity: AppCompatActivity() {
    var rolesData: RolesData? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        rolesData = savedInstanceState?.getSerializable("rolesData", RolesData::class.java)
        blackToTransparrentAnim(findViewById(R.id.fadeConstraint))
    }
    private fun nextRound(view: View, role: String){
        blackToTransparrentAnim(view)
        findViewById<TextView>(R.id.fadeText).text = "Просыпается $role"
        transparentToBlack(view)
    }
    private fun blackToTransparrentAnim(view: View){
        val animator = AlphaAnimation(1f, 0f)
        animator.fillAfter = true
        animator.duration = 2000
        view.startAnimation(animator)
    }
    private fun transparentToBlack(view: View){
        val animator = AlphaAnimation(0f, 1f)
        animator.fillAfter = true
        animator.duration = 2000
        view.startAnimation(animator)
    }
}