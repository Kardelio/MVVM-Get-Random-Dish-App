package bk.personal.com.getadish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var navController = findNavController(R.id.navHostFragment)
        bottomNavView.setupWithNavController(navController)
        var appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = setOf (
                        R.id.randomDishFragment,
                        R.id.previousDishFragment
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
