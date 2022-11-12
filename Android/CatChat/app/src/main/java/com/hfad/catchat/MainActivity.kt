package com.hfad.catchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set the toolbar instead of the appbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        /**
         * the navHostFragment from containerView from activity_main.xml
         */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //drawerLayout
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navView = findViewById<NavigationView>(R.id.nav_view)

        //connect the AppBar to the navController graph
        val builder = AppBarConfiguration.Builder(navController.graph)

        //add the drawer icon in the appBar
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()

        //apply the changes to the toolbar and add back arrow (up destination)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)

        //add the navigation to the DrawerLayout
        NavigationUI.setupWithNavController(navView, navController)
    }

    /**
     * add the menu panel to the toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        //onNavDestinationSelected() - Attempt to navigate to the NavDestination associated with this MenuItem.
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}