package com.example.chatwisetask.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chatwisetask.R
import com.example.chatwisetask.Repositories.Repository
import com.example.chatwisetask.UI.ImageViewModel
import com.example.chatwisetask.UI.ImageViewModelProviderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ImageViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val repo=Repository()
        val viewModelProviderFactory = ImageViewModelProviderFactory(repo)
        viewModel=ViewModelProvider(this, viewModelProviderFactory).get(ImageViewModel::class.java)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController=navHostFragment.navController
        setupActionBarWithNavController(navController)

    }

}