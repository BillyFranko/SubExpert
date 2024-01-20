package com.dicoding.subexpert1billy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.subexpert1billy.databinding.ActivityMainBinding
import com.dicoding.subexpert1billy.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (savedInstanceState == null){
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
//                .commit()
//        }

//        val appBarConfiguration = AppBarConfiguration.Builder(
//            R.id.navigation_home, R.id.navigation_favorite
//        ).build()

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
    }
}