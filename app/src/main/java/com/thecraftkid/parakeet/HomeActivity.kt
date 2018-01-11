package com.thecraftkid.parakeet

import android.arch.lifecycle.*
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.thecraftkid.parakeet.auth.UserManager
import com.thecraftkid.parakeet.ui.AssignmentListFragment
import com.thecraftkid.parakeet.ui.AssistantDisplayFragment
import com.thecraftkid.parakeet.ui.DashboardFragment
import com.thecraftkid.parakeet.ui.GradesListFragment
import com.thecraftkid.parakeet.util.IntentConstants
import kotlinx.android.synthetic.main.activity_home.*

/**
 * The main entry point for the app,showing a [AssistantDisplayFragment], a
 * [AssignmentListFragment], a [DashboardFragment], and a [GradesListFragment]
 *
 * @version 1.0.0
 * @since 1.0.0
 */
class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = HomeActivity::class.java.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.navigation_dashboard
        }
        getViewModel().getSelectionState().observe(this, Observer<Boolean> { isSelected ->
            tab_layout.visibility = if (isSelected!!) View.VISIBLE else View.GONE
            invalidateOptionsMenu()
        })
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        tab_layout.setupWithViewPager(ViewPager(this))
        fab.setOnClickListener { AssignmentCreationActivity.start(this) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId != R.id.navigation_grades)
            getViewModel().setShowTabs(false)
        switchFragment(when (item.itemId) {
            R.id.navigation_assistant -> AssistantDisplayFragment.newInstance(getUserId())
            R.id.navigation_recents -> AssignmentListFragment.newInstance()
            R.id.navigation_dashboard -> DashboardFragment.newInstance()
            R.id.navigation_grades -> {
                getViewModel().setShowTabs(true)
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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val authItem = menu!!.findItem(R.id.action_auth)
        if (UserManager.getInstance().isSignedIn) {
            authItem.title = getString(R.string.action_auth_sign_out)
            authItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        } else {
            authItem.title = getString(R.string.action_auth_sign_in)
            authItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_auth -> {
                val manager = UserManager.getInstance();
                if (manager.isSignedIn) {
                    manager.signOut(this)
                            .addOnSuccessListener(this, { recreate() })
                } else {
                    Log.i(TAG, "Starting sign in flow")
                    manager.signIn(this)
                }
                return true
            }
            R.id.action_add_class -> {
                ClassCreationActivity.start(this, getUserId())
                return true
            }
            R.id.action_settings -> {
                // TODO: Launch settings
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IntentConstants.RC_SIGN_IN -> {
                when (resultCode) {
                    RESULT_OK -> {
                        Log.i(TAG, "Sign in successful")
                        Toast.makeText(this, "You are now signed in", Toast.LENGTH_SHORT).show()
                        recreate()
                    }
                    RESULT_CANCELED -> {
                        Log.i(TAG, "Sign in canceled")
                    }
                }
            }
        }
    }

    private fun getViewModel(): HomeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    private fun getUserId(): String? = UserManager.getInstance().userId

    private class HomeViewModel : ViewModel() {

        private val selected: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

        fun getSelectionState(): LiveData<Boolean> = selected

        fun setShowTabs(show: Boolean) {
            selected.value = show
        }
    }
}
