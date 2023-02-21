package com.undeniabledreams.cloudbudgettracker.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.undeniabledreams.cloudbudgettracker.R
import com.undeniabledreams.cloudbudgettracker.core.BudgetTrackerUserDto
import com.undeniabledreams.cloudbudgettracker.dao.BudgetTrackerUserDao
import java.io.IOException

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val email: EditText = findViewById(R.id.edit_sign_up_email_id)
        val id: EditText = findViewById(R.id.edit_sign_up_user_id)
        val pass: EditText = findViewById(R.id.edit_sign_up_user_pass)
        val signUpBtn: Button = findViewById(R.id.sign_up_btn)


        signUpBtn.setOnClickListener {
            val userEmail: String = email.getText().toString()
            val userId: String = id.getText().toString()
            val userPassword: String = pass.getText().toString()

            val budgetTrackerUserDto = BudgetTrackerUserDto()
            budgetTrackerUserDto.setEmail(userEmail)
            budgetTrackerUserDto.setId(userId)
            budgetTrackerUserDto.setPassword(userPassword)
            val budgetTrackerUserDao = BudgetTrackerUserDao(this)

            try {
                budgetTrackerUserDao.insertIntoLoginTbl(budgetTrackerUserDto)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}