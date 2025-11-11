package com.example.mvc_kotlin.screens.common.navdrawer

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.views.BaseObservableViewMvc
import com.google.android.material.navigation.NavigationView

class NavDrawerViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
): BaseObservableViewMvc<NavDrawerViewMvc.Listener>(), NavigationView.OnNavigationItemSelectedListener, NavDrawerViewMvc {

    private var drawerLayout: DrawerLayout
    private var frameLayout: FrameLayout
    private var navigationView: NavigationView
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        drawerLayout.closeDrawers()
        if(menuItem.itemId == R.id.drawer_menu_latest_questions){
            for (listener in getListeners){
                listener.onQuestionListClicked()
            }
        }
        return false
    }

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_drawer, viewGroup, false))
        drawerLayout = findViewById(R.id.drawer_layout)
        frameLayout = findViewById(R.id.frame_content)
        navigationView = findViewById(R.id.nav_view)

        navigationView.setNavigationItemSelectedListener(this)
    }


    override fun openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun closeDrawer() {
        drawerLayout.closeDrawers()
    }

    override fun getFragmentFrame(): FrameLayout {
        return frameLayout
    }

    override fun isDrawerOpen(): Boolean {
        return drawerLayout.isDrawerOpen(GravityCompat.START)
    }

}