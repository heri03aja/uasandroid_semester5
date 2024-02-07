package com.example.uastoibul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var mUsername : EditText
    private lateinit var mPassword : EditText
    private lateinit var mBtn : Button
    private lateinit var mReg : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUsername = findViewById(R.id.edtUsername)
        mPassword = findViewById(R.id.edtPassword)
        mBtn = findViewById(R.id.btnLogin)

        mBtn.setOnClickListener{
            if(mUsername.text.toString().equals("admin") && mPassword.text.toString().equals("123")){

                val intent = Intent(this, RoomActivity::class.java)
                startActivity(intent)

            }else{

                Toast.makeText(this,"Wrong Username or Password", Toast.LENGTH_SHORT).show()

            }
        }
    }
}