package com.centennial.jovichenmcintyre_mapd711_001_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.centennial.jovichenmcintyre_mapd711_001_assignment2.exceptions.UserInputException
import com.centennial.jovichenmcintyre_mapd711_001_test.models.UserData
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var  age: EditText
    private lateinit var  weight: EditText
    private lateinit var  height: EditText
    private lateinit var  spinner: Spinner

    private var gender:String = "male"
    private lateinit var activityLevel:String
    private lateinit var loadImageCheckbox:CheckBox
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
        activityLevel = resources.getStringArray(R.array.string_exercise_frequency)[0]
        name = findViewById(R.id.name)
        age = findViewById(R.id.age)
        weight = findViewById(R.id.weight)
        height = findViewById(R.id.height)
        spinner = findViewById(R.id.spinner)
        loadImageCheckbox = findViewById(R.id.checkBox)



        ArrayAdapter.createFromResource(
            this,
            R.array.string_exercise_frequency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                activityLevel = resources.getStringArray(R.array.string_exercise_frequency)[position]
            }

        }
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

                if (loadImageCheckbox.isChecked) {
                    loadImage = true
                }

                var userData = UserData()
                userData.name = name.text.toString()
                userData.gender = gender
                userData.age = age.text.toString().toInt()
                userData.weight = weight.text.toString().toDouble()
                userData.height = height.text.toString().toDouble()

                when(activityLevel){
                    resources.getStringArray(R.array.string_exercise_frequency)[0]->userData.exciseFrequency = 1.2
                    resources.getStringArray(R.array.string_exercise_frequency)[1]->userData.exciseFrequency = 1.375
                    resources.getStringArray(R.array.string_exercise_frequency)[2]->userData.exciseFrequency = 1.55
                    resources.getStringArray(R.array.string_exercise_frequency)[3]->userData.exciseFrequency = 1.725
                }
                userData.shouldLoadImage = loadImage

                val newIntent = Intent(this,OutputActivity::class.java)
                startActivity(newIntent)

            }
        }
        catch (e: UserInputException) {
            showMessage(e.message.toString())
        }
    }
}