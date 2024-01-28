package com.android.fundamentals

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.fundamentals.workshop01.Workshop1LoginFragment
import com.android.fundamentals.workshop01.Workshop1ProfileFragment
import com.android.fundamentals.workshop01.Workshop1ViewModel
import com.android.fundamentals.workshop01.Workshop1ViewModelFactory
import com.android.fundamentals.workshop02.Workshop2Fragment
import com.android.fundamentals.workshop03.Ws03Fragment
import com.android.fundamentals.workshop03.antibonus.Ws03BonusFragment

class MainActivity : AppCompatActivity(), Router {

    val ws1ViewModel: Workshop1ViewModel by viewModels {
        Workshop1ViewModelFactory(
            applicationContext = applicationContext
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment(SelectWorkshopFragment.newInstance(), addToBackStack = false)
        }
    }

    override fun openWorkshop1() = checkUserAndOpenFragmentForWS1()

    override fun openWorkshop2() = openFragment(Workshop2Fragment.newInstance())

    override fun openWorkshop3() = openFragment(Ws03BonusFragment.newInstance())

    private fun checkUserAndOpenFragmentForWS1() {
        if (!ws1ViewModel.checkUserIsLoggedIn()) {
            openFragment(Workshop1LoginFragment.newInstance())
        } else {
            openFragment(Workshop1ProfileFragment.newInstance())
        }
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(fragment::class.java.name)
        }

        transaction.commit()
    }
}