package com.example.mafia

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mafia.roles.Role
import com.example.mafia.roles.Roles

class RolesActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roles_activity)
        generateRolesPlanks()
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun generateRolesPlanks(){
        var count = 0
        val baseID = 1000
        Roles.getAllRoles().forEach{role ->
            val roleLayout = LinearLayout(this)
            roleLayout.setBackgroundResource(R.drawable.role_bar_shape)
            roleLayout.id = baseID + count
            roleLayout.orientation = LinearLayout.HORIZONTAL
            roleLayout.isClickable = true
            roleLayout.setOnClickListener { onClick(role) }
            roleLayout.stateListAnimator = Button(this).stateListAnimator


            val iconView = ImageView(this)
            iconView.setImageDrawable(getDrawable(role.icon))
            iconView.layoutParams = ViewGroup.LayoutParams(180, 180)
            roleLayout.addView(iconView)


            val roleName = TextView(this)
            roleName.text = role.name
            roleName.layoutParams = ViewGroup.LayoutParams(700, LayoutParams.MATCH_PARENT)
            roleName.textAlignment = View.TEXT_ALIGNMENT_CENTER
            roleName.gravity = Gravity.CENTER_VERTICAL
            roleLayout.addView(roleName)
            roleName.setTextAppearance(R.style.InfoRolesTextStyle)


            val roleListViewLayout = findViewById<LinearLayout>(R.id.role_listview_linear)
            roleListViewLayout.addView(roleLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200))

            count++
        }
    }
    private fun onClick(role: Role){
        val alertBuilder = AlertDialog.Builder(this)
        val alertLayout = layoutInflater.inflate(R.layout.role_description_alert, null)
        alertBuilder.setView(alertLayout)
        alertLayout.findViewById<ImageView>(R.id.alert_role_icon).setImageResource(role.icon)
        alertLayout.findViewById<TextView>(R.id.alert_role_name).text = role.name
        alertLayout.findViewById<TextView>(R.id.alert_role_description).text = role.description
        alertBuilder.create().show()
    }
}