package com.android.academy.fundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.workshop_1.WS01ThreadsTaskFragment
import com.android.academy.fundamentals.workshop_2.WS02FirstCoroutineTaskFragment
import com.android.academy.fundamentals.workshop_3.WS03CoroutinesProblemFragment
import com.android.academy.fundamentals.workshop_4.WS04ErrorHandlingProblemFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container_view, WS04ErrorHandlingProblemFragment())
                .commit()
        }
    }
}