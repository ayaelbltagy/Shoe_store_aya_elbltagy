package com.example.theshoestore.ui.views

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.theshoestore.R
import com.example.theshoestore.databinding.ActivityMainBinding
import com.example.theshoestore.helper.PreferenceHelper
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerNavigation: NavigationView
    private lateinit var helper: PreferenceHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = PreferenceHelper(this)

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        drawerNavigation = findViewById(R.id.navigation_drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout, R.string.nav_open, R.string.nav_close
        )

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerNavigation.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            updateUserInfo()
            false
        })

    }

    // drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

        }
        return true
    }

    // for navigation
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    fun updateUserInfo() {

        if (!helper.getEmail().equals("") || !helper.getEmail().equals(null)) {
            helper.setEmail("")
            Toast.makeText(this, "you logged out! ... see you soon", Toast.LENGTH_LONG).show()

        }

    }


}

