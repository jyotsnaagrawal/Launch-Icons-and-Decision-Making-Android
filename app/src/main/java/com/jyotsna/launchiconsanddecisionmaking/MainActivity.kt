package com.jyotsna.launchiconsanddecisionmaking

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var editTextAmount: EditText
    private lateinit var textViewConvertedAmount: TextView

    // Exchange rate for demonstration purposes
    private val exchangeRate = 0.85 // 1 USD = 0.85 currency (for example, Euro)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hide default title

        editTextAmount = findViewById(R.id.editTextAmount)
        textViewConvertedAmount = findViewById(R.id.textViewConvertedAmount)
        val buttonConvertToCurrency = findViewById<Button>(R.id.buttonConvertToCurrency)
        val buttonConvertToUSD = findViewById<Button>(R.id.buttonConvertToUSD)

        buttonConvertToCurrency.setOnClickListener {
            val amountInUSD = editTextAmount.text.toString().toDoubleOrNull()
            if (amountInUSD != null) {
                // Perform conversion to currency
                val convertedAmount = convertToCurrency(amountInUSD)
                textViewConvertedAmount.text = "Converted Amount: $convertedAmount"

                // Display toast message for values over $10,000.00
                if (convertedAmount > 10000.00) {
                    showToastMessage("Converted amount is over $10,000.00!")
                }
            } else {
                showToastMessage("Please enter a valid amount!")
            }
        }

        buttonConvertToUSD.setOnClickListener {
            val amountInCurrency = textViewConvertedAmount.text.toString().substringAfter(":").trim().toDoubleOrNull()
            if (amountInCurrency != null) {
                // Perform conversion to USD
                val convertedAmount = convertToUSD(amountInCurrency)
                editTextAmount.setText(convertedAmount.toString())
            } else {
                showToastMessage("Please convert to currency first!")
            }
        }
    }

    private fun convertToCurrency(amountInUSD: Double): Double {

        return amountInUSD * exchangeRate
    }

    private fun convertToUSD(amountInCurrency: Double): Double {

        return amountInCurrency / exchangeRate
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
