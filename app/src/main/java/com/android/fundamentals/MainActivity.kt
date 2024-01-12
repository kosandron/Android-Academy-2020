package com.android.fundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.fundamentals.workshop01.WS01ActorsFragment
import com.android.fundamentals.workshop02.WS02ActorsFragment
import com.android.fundamentals.workshop03.WS03ActorsFragment
import com.android.fundamentals.workshop04.WS04DiffUtilsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    // TODO: Change fragment according to the workshop #
                    .replace(R.id.container, WS04DiffUtilsFragment.newInstance())
                    .commit()
        }
    }
}