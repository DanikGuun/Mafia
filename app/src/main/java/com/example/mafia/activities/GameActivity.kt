package com.example.mafia.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mafia.GameData
import com.example.mafia.Player
import com.example.mafia.R
import com.example.mafia.roles.Role
import com.example.mafia.roles.Roles

class GameActivity: AppCompatActivity() {
    private var gameData: GameData? = null
    private val rolesQueue = Roles.getRolesQueue()
    private var currentRoleIndex = -1
    private val journalistSteps = ArrayList<Player>()
    private var isEndOfNight = false

    @Suppress("DEPRECATION")
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        gameData = intent.getSerializableExtra("rolesData") as GameData
        blackToTransparentAnim(findViewById(R.id.fadeConstraint))
        generatePlayersWithExcluded(null, false)
        findViewById<TextView>(R.id.gameToolbarText).text = "знакомство"
        findViewById<Button>(R.id.gameStartButton).setOnClickListener{ startGame() }
    }

    @SuppressLint("SetTextI18n")
    private fun nextRoundFade(role: Role){
        Log.d("TAG", "nextRoundFade: ${currentRoleIndex}")
        //делает переход на следующий раунд, вызывать в последнююю очередь
        val fadeView = findViewById<ConstraintLayout>(R.id.fadeConstraint)
        transparentToBlackAnim(fadeView)

        if (isEndOfNight){
            findViewById<TextView>(R.id.fadeText).text = "Город просыпается"
            Log.d("TAG", "nextRoundFade: ${gameData!!.sumUpNight()}")
        }
        else findViewById<TextView>(R.id.fadeText).text = "Просыпается ${role.name}"

        android.os.Handler(Looper.getMainLooper()).postDelayed( {
            blackToTransparentAnim(fadeView)
            findViewById<LinearLayout>(R.id.gamePlayersList).removeAllViews()

            if(isEndOfNight){
                findViewById<TextView>(R.id.gameToolbarText).text = "Итоги"
                Log.d("TAG", "nextRoundFade: ${gameData!!.steps}")
            }
            else{
                findViewById<TextView>(R.id.gameToolbarText).text = role.name
                generatePlayersWithExcluded(role)
            }
        }, 2)

    }

    private fun generatePlayersWithExcluded(excludedRole: Role?, needClick: Boolean = true){
        for(player in gameData!!.playersList){
            if(player.role == excludedRole) continue
            generatePlayer(player, needClick)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun generatePlayer(player: Player, needClick: Boolean = true){
        val linearScroll = findViewById<LinearLayout>(R.id.gamePlayersList)

        val playerPlank = LinearLayout(this)
        playerPlank.orientation = LinearLayout.HORIZONTAL
        playerPlank.background = getDrawable(R.drawable.role_bar_shape)

        if(needClick){
            if(rolesQueue[currentRoleIndex] == Roles.Journalist()){
                playerPlank.setOnClickListener{ onJournalistClick(player) }
            }
            else{
                playerPlank.setOnClickListener{ onPlayerClick(player) }
            }
            playerPlank.stateListAnimator = Button(this).stateListAnimator
        }

        val icon = ImageView(this)
        icon.setImageDrawable(getDrawable(player.role.icon))
        playerPlank.addView(icon, LayoutParams(200, 200))

        val playerName = TextView(this)
        playerName.text = player.name
        playerName.setTextAppearance(R.style.PlayerNameText)
        playerName.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        playerName.gravity = Gravity.CENTER_VERTICAL
        playerName.textSize = 24f
        playerPlank.addView(playerName, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))

        linearScroll.addView(playerPlank, 0, LayoutParams(LayoutParams.MATCH_PARENT, 200))
    }

    private fun onPlayerClick(player: Player){
        gameData!!.steps[rolesQueue[currentRoleIndex]] = arrayListOf(gameData!!.getPlayersWithRole(rolesQueue[currentRoleIndex]), arrayListOf(player))
        currentRoleIncrement()
        nextRoundFade(rolesQueue[currentRoleIndex])
    }

    private fun onJournalistClick(player: Player){
        journalistSteps.add(player)
        if(journalistSteps.size == 2){
            gameData!!.steps[rolesQueue[currentRoleIndex]] = arrayListOf(gameData!!.getPlayersWithRole(rolesQueue[currentRoleIndex]), journalistSteps)
            currentRoleIncrement()
            nextRoundFade(rolesQueue[currentRoleIndex])
        }
    }

    private fun startGame(){
        currentRoleIncrement()
        nextRoundFade(rolesQueue[currentRoleIndex])
    }

    private fun blackToTransparentAnim(view: View){
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
    private fun currentRoleIncrement(){
        if(currentRoleIndex < Roles.ROLES_COUNT-1) currentRoleIndex++
        else isEndOfNight = true
    }
}