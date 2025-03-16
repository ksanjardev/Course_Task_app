package uz.sanjar.coursetaskapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val sharedPref = getSharedPreferences("app_pref", MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("is_first_time", true)

        // Get NavController from NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        // Initialize BottomNavigationView
        bottomNav = findViewById(R.id.bottomNavigationView)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onBoardingScreen, R.id.loginScreen -> {
                    bottomNav.visibility = View.GONE
                }

                else -> bottomNav.visibility = View.VISIBLE
            }
        }
        // Setup BottomNavigationView with NavController
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemReselectedListener {}

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph) as NavGraph
        if (!isFirstTime) {
            navGraph.setStartDestination(R.id.nav_home)
        } else {
            navGraph.setStartDestination(R.id.onBoardingScreen)
        }
        navController.graph = navGraph
    }
}