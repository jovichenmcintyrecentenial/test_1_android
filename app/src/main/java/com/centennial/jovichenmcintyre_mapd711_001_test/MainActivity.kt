package com.centennial.jovichenmcintyre_mapd711_001_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var  age: EditText
    private lateinit var  weight: EditText
    private lateinit var  height: EditText

    private lateinit var gender:String;
    private lateinit var activityLevel:String
    private var loadImage:Boolean = false


    private fun isDataValid(): Boolean {
        if(name.text.trim().isEmpty()){
            throw UserInputException("Please fill out the name field")
        }
        if(age.text.trim().isEmpty()){
            throw UserInputException("Please fill out the age field")
        }
        if(weight.text.trim().isEmpty()){
            throw UserInputException("Please fill out the weight field")
        }
        if(height.text.trim().isEmpty()){
            throw UserInputException("Please fill out the height field")
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSelectRadioButton(view: View) {

        if(view is RadioButton){
            when(view.id){
                R.id.male ->gender = "male"
                R.id.female -> gender = "female"
            }
        }

    }
    private fun showMessage(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }


    fun onSubmit(view: View) {
        try{
            if(isDataValid()){

                val newIntent = Intent(this,OutputActivity::class.java)
                startActivity(newIntent)

            }
        }
        catch (e: UserInputException) {
            showMessage(e.message.toString())
        }
    }
}