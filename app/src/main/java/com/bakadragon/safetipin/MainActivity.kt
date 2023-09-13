package com.bakadragon.safetipin

import android.Manifest
import android.app.Notification.Action
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var sos: Button
    lateinit var nearpols: Button
    lateinit var saferoutes: Button
    lateinit var fakecall: Button
    lateinit var womenSHG: Button

    lateinit var userMessage:String
    lateinit var userNumber: String
    lateinit var fakeNumber:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userNumber="8797335407"
        userMessage="help me ahhhhhhh here is my location"
        fakeNumber="9791284133"
        sos=findViewById(R.id.buttonSOS)
        nearpols=findViewById(R.id.buttonNearestPolice)
        saferoutes=findViewById(R.id.buttonSafeRoutes)
        fakecall=findViewById(R.id.buttonFakeCall)
        womenSHG=findViewById(R.id.buttonWomensSelfHelp)
        sos.setOnClickListener {
            sendSOS(userNumber,userMessage)
        }
        nearpols.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("https://maps.google.com/maps?client=ms-android-xiaomi-rvo2&sca_esv=564912168&output=search&q=police+station+near+me&source=lnms&entry=mc&sa=X&ved=2ahUKEwjp0peQ4KaBAxUl1zgGHQ3OD-IQ0pQJegQIDBAB")
            startActivity(intent)
        }
        fakecall.setOnClickListener {
            makeFakeCall(fakeNumber)
        }
        womenSHG.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data=Uri.parse("https://maps.google.com/maps?q=women%27s+self+help+groups+near+me&sca_esv=564912168&sxsrf=AM9HkKkGtzGZUvzxrPWa1V3o53QtJUut0Q:1694580075578&gs_lp=EhNtb2JpbGUtZ3dzLXdpei1zZXJwIgx3b21lbidzIHNlbGYqAggAMgoQIxiKBRjJAxgnMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABEjHTVDPLFi-PnADeAGQAQCYAdYCoAHyA6oBBzAuMS4wLjG4AQHIAQD4AQGoAg_CAgoQABhHGNYEGLADwgIKEAAYigUYsAMYQ8ICBxAjGOoCGCfCAg0QLhiKBRixAxiDARhDwgIREC4YigUYsQMYgwEYxwEY0QPCAggQLhiABBixA8ICCBAuGLEDGIAEwgILEC4YigUYsQMYgwHCAgsQLhiABBixAxiDAcICCxAAGIAEGLEDGIMB4gMEGAAgQYgGAZAGEQ&um=1&ie=UTF-8&sa=X&ved=2ahUKEwjAneyi6qaBAxWYSmwGHY4CCaUQ_AUoA3oECAYQAw")
            startActivity(intent)
        }

    }
    fun makeFakeCall(fakeNumber:String){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) !=
        PackageManager.PERMISSION_GRANTED
    ) {
        // Permission not granted, request permission
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),
            1
        )
    } else {
        // make call
        val intent=Intent(Intent.ACTION_CALL)
            intent.data=Uri.parse("tel:${fakeNumber}")
            startActivity(intent)
    }
    }

    fun sendSOS(userNumber:String,userMessage:String){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            // Permission not granted, request permission
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),
                1
            )
        } else {
            // Permission granted, send SMS
            val smsManager: SmsManager
            smsManager= this.getSystemService(SmsManager::class.java)
            smsManager.sendTextMessage(userNumber,null,userMessage,null,null)
            Toast.makeText(this, "Message sent", Toast.LENGTH_LONG).show()
        }
    }
}
