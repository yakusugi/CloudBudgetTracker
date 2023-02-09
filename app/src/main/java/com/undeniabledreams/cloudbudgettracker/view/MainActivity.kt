package com.undeniabledreams.cloudbudgettracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.undeniabledreams.cloudbudgettracker.R
import com.undeniabledreams.cloudbudgettracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(DashFragment())

        binding.bottomNavView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.dash -> replaceFragment(DashFragment())
                R.id.stats -> replaceFragment(StatsFragment())
                R.id.search -> replaceFragment(SearchFragment())
                R.id.convert -> replaceFragment(ConverterFragment())

                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}