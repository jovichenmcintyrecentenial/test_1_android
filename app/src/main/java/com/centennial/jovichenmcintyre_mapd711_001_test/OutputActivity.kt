package com.centennial.jovichenmcintyre_mapd711_001_test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.centennial.jovichenmcintyre_mapd711_001_test.models.UserData
import com.google.gson.Gson

class OutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        val sharedPref = this.getSharedPreferences(
            resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val userDataJson= sharedPref.getString("user_data",null)
        if(userDataJson != null){
            var userData = Gson().fromJson(userDataJson, UserData::class.java)

        }
    }
}