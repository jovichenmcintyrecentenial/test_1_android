package com.centennial.jovichenmcintyre_mapd711_001_test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.centennial.jovichenmcintyre_mapd711_001_test.models.UserData
import com.google.gson.Gson

class OutputActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        var nameTextView = findViewById<TextView>(R.id.nameTextView)
        var brmTextView = findViewById<TextView>(R.id.bmr)
        var dailyIntake = findViewById<TextView>(R.id.daily_intake)
        var givenInputs = findViewById<TextView>(R.id.given_inputs)
        var imageView = findViewById<ImageView>(R.id.imageView)

        val sharedPref = this.getSharedPreferences(
            resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val userDataJson= sharedPref.getString("user_data",null)
        if(userDataJson != null){
            var userData = Gson().fromJson(userDataJson, UserData::class.java)
            nameTextView.text = "Hello "+userData.name.toString()+"!"

            var tempStr = ""
            tempStr += "  Age: "+userData.age
            tempStr += "\n  Weight: "+userData.weight
            tempStr += "\n  Height: "+userData.height
            tempStr += "\n  Activity Level: "+userData.activityLevel

            givenInputs.text = tempStr

            if(userData.shouldLoadImage){
                imageView.visibility = View.VISIBLE
            }
            else{
                imageView.visibility = View.GONE
            }


            //male  (10 * weight [kg]) + (6.25 * height [cm]) − (5 * age [years]) + 5
            var bmr = (10 * userData.weight!!)+(6.25 * userData.height!!) - (5 * userData.age!!) + 5

            if(userData.gender == "female"){
                //BMR for Women = (10 * weight [kg]) + (6.25 * size [cm]) − (5 * age [years]) - 161
                bmr = (10 * userData.weight!!)+(6.25 * userData.height!!) - (5 * userData.age!!) - 161
            }
            var intakeInCalories = bmr * userData.exciseFrequency!!

            brmTextView.text = brmTextView.text.toString() + bmr.toString() + " Calories/day "
            dailyIntake.text = dailyIntake.text.toString() + intakeInCalories.toString() + " Calories "

        }
    }
}