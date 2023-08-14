package com.example.mafia

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.ConstraintSet.LEFT
import androidx.constraintlayout.widget.ConstraintSet.RIGHT
import androidx.constraintlayout.widget.ConstraintSet.TOP
import com.example.mafia.roles.Roles
import com.google.android.material.bottomappbar.BottomAppBar

class RolesActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roles_activity)
        generateRolesPlanks()
    }
    private fun generateRolesPlanks(){
        var count = 0
        val baseID = 1000
        for (role in Roles.getAllRoles()){
            val roleLayout = LinearLayout(this)
            roleLayout.setBackgroundResource(R.drawable.role_bar_shape)
            roleLayout.id = baseID + count

            val roleListViewLayout = findViewById<LinearLayout>(R.id.role_listview_linear)
            roleListViewLayout.addView(roleLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200))

            count++
        }
    }
}