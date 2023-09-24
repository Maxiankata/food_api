package com.example.myapplication
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.room.FoodDB

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences:SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onDestroy() {
        super.onDestroy()
        val drawerLayout: DrawerLayout = binding.drawerLayout
    }
    fun lockDrawer() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
    fun unlockDrawer() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            FoodDB::class.java, "food-base"
        ).build()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fab.apply {
            visibility = GONE
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.favorite_button1, R.id.Home, R.id.theme_moon, R.id.theme_sun),
            drawerLayout
        )
        sharedPreferences = getSharedPreferences("MODE",Context.MODE_PRIVATE)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)
        val menu: Menu = navView.menu
        val moonMenuItem: MenuItem = menu.findItem(R.id.theme_moon)
        val sunMenuItem: MenuItem = menu.findItem(R.id.theme_sun)
        val favoritesMenuItem: MenuItem = menu.findItem(R.id.favorite_button1)
        val informationIcon = menu.findItem(R.id.Home)
        val informationExtended :MenuItem = menu.findItem(R.id.information_extended)
        val textView = informationExtended.actionView as? TextView
        val isMoonIconVisible = sharedPreferences.getBoolean("moonIconVisible", true)

        informationExtended.isVisible=false
        moonMenuItem.isVisible = !isMoonIconVisible
        sunMenuItem.isVisible = isMoonIconVisible
        var informationTextBlack: Boolean = !isMoonIconVisible

        if (informationTextBlack){
            textView?.setTextColor(ContextCompat.getColor(this, R.color.black))
        }else(
                textView?.setTextColor(ContextCompat.getColor(this, R.color.white))
                )
        informationIcon.setOnMenuItemClickListener {
            informationExtended.isVisible = !informationExtended.isVisible
            R.id.information_extended
            true
        }

        moonMenuItem.apply { setOnMenuItemClickListener {
            editor.putBoolean("nightMode", true)
            editor.putBoolean("moonIconVisible", true)
            editor.apply()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            moonMenuItem.isVisible = false
            sunMenuItem.isVisible = true
            true }
        }

        sunMenuItem.apply {
            setOnMenuItemClickListener {
            editor.putBoolean("nightMode", false)
            editor.putBoolean("moonIconVisible", false)
            editor.apply()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            moonMenuItem.isVisible = true
            sunMenuItem.isVisible = false
            true }
        }
        favoritesMenuItem.apply {
            setOnMenuItemClickListener{
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.fragmentFavoritesInflater)

                true
            }
        }

    }

    fun setFabVisibility(visibility: Int) {
        binding.appBarMain.fab.visibility = visibility
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    companion object {
        private lateinit var db: FoodDB
        fun getDatabaseInstance(): FoodDB {
            return db
        }
    }
}