package com.undeniabledreams.cloudbudgettracker.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.undeniabledreams.cloudbudgettracker.R
import com.undeniabledreams.cloudbudgettracker.databinding.ActivityMainBinding

@SuppressLint("ResourceType")
class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.animator.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.animator.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.animator.from_bottom_anim)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.animator.to_bottom_anim)}

    private var clicked = false
    private lateinit var addBtn: FloatingActionButton
    private lateinit var storeBtn: FloatingActionButton
    private lateinit var productBtn: FloatingActionButton

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

        addBtn = findViewById(R.id.add_btn)
        storeBtn = findViewById(R.id.store_btn)
        productBtn = findViewById(R.id.product_type_btn)

        addBtn.setOnClickListener {
            onAddBtnClicked()
        }

        storeBtn.setOnClickListener {
            Toast.makeText(this, "EditBtn Clicked", Toast.LENGTH_SHORT).show()
        }

        productBtn.setOnClickListener {
            Toast.makeText(this, "ImgBtn Clicked", Toast.LENGTH_SHORT).show()
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun onAddBtnClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            storeBtn.startAnimation(fromBottom)
            productBtn.startAnimation(fromBottom)
            addBtn.startAnimation(rotateOpen)
        } else {
            storeBtn.startAnimation(toBottom)
            productBtn.startAnimation(toBottom)
            addBtn.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            storeBtn.visibility = View.VISIBLE
            productBtn.visibility = View.VISIBLE
        } else {
            storeBtn.visibility = View.INVISIBLE
            productBtn.visibility = View.INVISIBLE
        }
    }



}