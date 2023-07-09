package com.example.moviesappv2.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviesappv2.R
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()
    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        navController = navHostFragment!!.findNavController()
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )
        popularMoviesViewModel.moviesList.observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(this, "Success, see the log!", Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                    Log.d("MainActivity", "onCreate: ${resource.message}")
                }
            }
        }

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