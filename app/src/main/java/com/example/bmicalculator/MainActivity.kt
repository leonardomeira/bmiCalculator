package com.example.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //Declaring binding variable to work with IDs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Basic binding configurations
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Getting buttons IDs
        val btnReset = binding.btnReset
        val btnCalculate = binding.btnCalculate

        //Reset button logic
        btnReset.setOnClickListener {
            val resetWeight = binding.weight
            val resetHeight = binding.height
            val resetFinalResult = binding.finalResult

            resetWeight.setText("")
            resetHeight.setText("")
            resetFinalResult.setText("")
        }

        //Calculate button logic - preventing empty calculation
        btnCalculate.setOnClickListener {
            val weight = binding.weight.text.toString()
            val height = binding.height.text.toString()

            if (weight.isEmpty() && height.isEmpty()) {
                Toast.makeText(this, "Please enter your data", Toast.LENGTH_LONG).show()
            } else if (weight.isEmpty()) {
                Toast.makeText(this, "Please enter weight", Toast.LENGTH_LONG).show()
            } else if (height.isEmpty()) {
                Toast.makeText(this, "Please enter your height", Toast.LENGTH_LONG).show()
            } else  {
                BMICalculation()
            }
        }
    }


    /* -----------------------------------------------
    -------- BMI Calculation and Display Logic -------
    ------------------------------------------------*/


    private fun BMICalculation() {

        //------Equation logic------//


        val interWeight = binding.weight.text //Getting weight as a string
        val interHeight = binding.height.text //Getting height as a string

        val finalWeight = interWeight.toString().toDouble() //Converting weight to Double
        val finalHeight = interHeight.toString().toDouble() //Converting height to Double
        var bmi = finalWeight / (finalHeight * finalHeight) //BMI Equation
        bmi = Math.floor(bmi * 100) / 100 //Rounding BMI to 2 decimals
        val finalResult = binding.finalResult //Getting finalResult element

        //------Display logic------//


        // Color declarations to be used in the finalResult
        val greenTxt = ContextCompat.getColor(this, R.color.green_700)
        val yellowTxt = ContextCompat.getColor(this, R.color.yellow)
        val orangeTxt = ContextCompat.getColor(this, R.color.orange)
        val redTxt = ContextCompat.getColor(this, R.color.red)
        val darkRedTxt = ContextCompat.getColor(this, R.color.dark_red)

        when {

            bmi <= 18.4 -> {
                finalResult.setText("Your BMI: $bmi\nYou're underweight")
                finalResult.setTextColor(yellowTxt)
            }
            bmi <= 24.9 -> {
                finalResult.setText("Your BMI: $bmi\nYou're at a healthy weight\n(Normal Range)")
                finalResult.setTextColor(greenTxt)
            }
            bmi <= 29.9 -> {
                finalResult.setText("Your BMI: $bmi\nYou're overweight")
                finalResult.setTextColor(yellowTxt)
            }
            bmi <= 34.9 -> {
                finalResult.setText("Your BMI: $bmi\nYou're obese (Class I)")
                finalResult.setTextColor(orangeTxt)
            }
            bmi <= 39.9 -> {
                finalResult.setText("Your BMI: $bmi\nYou're obese (Class II)")
                finalResult.setTextColor(redTxt)
            }
            else -> {
                finalResult.setText("Your BMI: $bmi\nYou're morbidly obese (Class III)")
                finalResult.setTextColor(darkRedTxt)
            }
        }
    }
}