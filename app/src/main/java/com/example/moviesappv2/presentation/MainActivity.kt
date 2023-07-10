package com.example.moviesappv2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviesappv2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        navController = navHostFragment!!.findNavController()
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )


        // TODO: remove if not willing to use actionbar in the design.
        /*        appBarConfiguration = AppBarConfiguration(
                    setOf(R.id.homeFragment, R.id.searchFragment,  R.id.favoritesFragment)
                )
                setupActionBarWithNavController(navController, appBarConfiguration)*/
    }

    // TODO: remove if not willing to use actionbar in the design.
    /*    override fun onSupportNavigateUp(): Boolean {
            return navController.navigateUp(appBarConfiguration)
        }*/
}