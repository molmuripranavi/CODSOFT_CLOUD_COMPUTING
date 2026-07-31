package com.molmuripranavi.smartbuscloud.activities.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.molmuripranavi.smartbuscloud.R

class RoleSelectionActivity : AppCompatActivity() {

    private lateinit var cardPassenger: CardView
    private lateinit var cardAdmin: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        cardPassenger = findViewById(R.id.cardPassenger)
        cardAdmin = findViewById(R.id.cardAdmin)

        // Passenger Login
        cardPassenger.setOnClickListener {

            startActivity(
                Intent(this, PassengerLoginActivity::class.java)
            )
        }

        // Admin Login
        cardAdmin.setOnClickListener {

            startActivity(
                Intent(this, AdminLoginActivity::class.java)
            )
        }
    }
}