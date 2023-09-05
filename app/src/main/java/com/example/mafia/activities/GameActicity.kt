package com.example.mafia.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mafia.R
import com.example.mafia.GameData
import com.example.mafia.roles.Role
import com.example.mafia.roles.Roles
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameActicity: AppCompatActivity() {
    private var gameData: GameData? = null

    @Suppress("DEPRECATION")
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        gameData = intent.getSerializableExtra("rolesData") as GameData
        blackToTransparrentAnim(findViewById(R.id.fadeConstraint))
        generatePlayersWithExcluded(null)
    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun nextRoundFade(view: View, role: String){
        findViewById<TextView>(R.id.fadeText).text = "Просыпается $role"
        transparentToBlackAnim(view)
        GlobalScope.launch {
            delay(2500)
            blackToTransparrentAnim(view)
        }
    }
    private fun generatePlayersWithExcluded(excludedRole: Role?){
        for(key in gameData!!.rolesMap.keys){
            if(excludedRole == gameData!!.rolesMap[key]) continue
            generatePlayer(key, gameData!!.rolesMap[key]!!)
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun generatePlayer(name: String, role: Role){
        val linearScroll = findViewById<LinearLayout>(R.id.gamePlayersList)

        val playerPlank = LinearLayout(this)
        playerPlank.orientation = LinearLayout.HORIZONTAL
        playerPlank.background = getDrawable(R.drawable.role_bar_shape)

        val icon = ImageView(this)
        icon.setImageDrawable(getDrawable(role.icon))
        playerPlank.addView(icon, LayoutParams(200, 200))

        val playerName = TextView(this)
        playerName.text = name
        playerName.setTextAppearance(R.style.PlayerNameText)
        playerName.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        playerName.gravity = Gravity.CENTER_VERTICAL
        playerName.textSize = 24f
        playerPlank.addView(playerName, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))

        linearScroll.addView(playerPlank, 0, LayoutParams(LayoutParams.MATCH_PARENT, 200))
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