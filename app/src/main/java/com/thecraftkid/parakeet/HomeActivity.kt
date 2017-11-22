package com.thecraftkid.parakeet

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.thecraftkid.parakeet.ui.AssignmentListFragment
import com.thecraftkid.parakeet.ui.AssistantDisplayFragment
import com.thecraftkid.parakeet.ui.DashboardFragment
import com.thecraftkid.parakeet.ui.GradesListFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * The main entry point for the app,showing a [AssistantDisplayFragment], a
 * [AssignmentListFragment], a [DashboardFragment], and a [GradesListFragment]
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/17)
 */
class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getSelectionState().observe(this, Observer<Boolean> { isSelected ->
            tab_layout.visibility = if (isSelected!!) View.VISIBLE else View.GONE
        })
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        tab_layout.setupWithViewPager(ViewPager(this))
        fab.setOnClickListener { AssignmentCreationActivity.start(this) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId != R.id.navigation_grades)
            viewModel.setShowTabs(false)
        switchFragment(when (item.itemId) {
            R.id.navigation_assistant -> AssistantDisplayFragment.newInstance(getUserId())
            R.id.navigation_recents -> AssignmentListFragment.newInstance()
            R.id.navigation_dashboard -> DashboardFragment.newInstance()
            R.id.navigation_grades -> {
                viewModel.setShowTabs(true)
                GradesListFragment.newInstance(getUserId())
            }
            else -> throw IllegalStateException("Unknown selection")
        })
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // TODO: Launch settings
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    private fun getUserId(): String {
        // TODO: Move auth stuff into separate class
        return ""
    }

    private class HomeViewModel : ViewModel() {

        private val selected: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

        fun getSelectionState(): LiveData<Boolean> = selected

        fun setShowTabs(show: Boolean) {
            selected.value = show
        }
    }
}
