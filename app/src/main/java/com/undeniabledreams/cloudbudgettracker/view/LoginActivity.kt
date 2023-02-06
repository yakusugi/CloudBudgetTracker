package com.undeniabledreams.cloudbudgettracker.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.undeniabledreams.cloudbudgettracker.R
import com.undeniabledreams.cloudbudgettracker.core.BudgetTrackerUserDto
import com.undeniabledreams.cloudbudgettracker.dao.BudgetTrackerUserDao
import java.io.IOException

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val id: EditText = findViewById(R.id.edit_user_id)
        val pass: EditText = findViewById(R.id.edit_user_pass)
        val signInBtn: Button = findViewById(R.id.sign_in_btn)
        val signUpText: TextView = findViewById(R.id.sign_in_text)

        signUpText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        signInBtn.setOnClickListener {
            val userId: String = id.getText().toString()
            val userPassword: String = pass.getText().toString()
            val budgetTrackerUserDto = BudgetTrackerUserDto()
            budgetTrackerUserDto.setId(userId)
            budgetTrackerUserDto.setPassword(userPassword)

            val budgetTrackerUserDao = BudgetTrackerUserDao(this)

            try {
                budgetTrackerUserDao.logIn(budgetTrackerUserDto) { result ->
                    if (result == 1) {
                        Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: IOException) {
                Toast.makeText(this@LoginActivity, "Error in logging in!", Toast.LENGTH_SHORT).show()
                e.printStackTrace()

            }
        }

    }
}