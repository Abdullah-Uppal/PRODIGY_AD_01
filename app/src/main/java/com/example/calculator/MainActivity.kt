package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private var currentDisplay = ""
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setClickListeners()
  }

  private fun setClickListeners() {
    binding.apply {
      one.setOnClickListener { appendToDisplay("1") }
      two.setOnClickListener { appendToDisplay("2") }
      three.setOnClickListener { appendToDisplay("3") }
      four.setOnClickListener { appendToDisplay("4") }
      five.setOnClickListener { appendToDisplay("5") }
      six.setOnClickListener { appendToDisplay("6") }
      seven.setOnClickListener { appendToDisplay("7") }
      eight.setOnClickListener { appendToDisplay("8") }
      nine.setOnClickListener { appendToDisplay("9") }
      zero.setOnClickListener { appendToDisplay("0") }
      add.setOnClickListener { appendToDisplay(" + ") }
      subtract.setOnClickListener { appendToDisplay(" - ") }
      multiply.setOnClickListener { appendToDisplay(" × ") }
      divide.setOnClickListener { appendToDisplay(" / ") }
      clear.setOnClickListener { clearDisplay() }
      equal.setOnClickListener { evaluateExpression() }
      del.setOnClickListener { deleteLastChar() }
      dot.setOnClickListener { appendToDisplay(".") }
    }
  }

  private fun evaluateExpression() {
    try {
      val expression = ExpressionBuilder(currentDisplay.replace('×', '*')).build()
      val result = expression.evaluate()
      val longResult = result.toLong()
      if (result == longResult.toDouble()) {
        currentDisplay = longResult.toString()
        binding.display.text = currentDisplay
      } else {
        currentDisplay = result.toString()
        binding.display.text = currentDisplay
      }
    } catch (e: Exception) {
      Log.d("Exception", " message: " + e.message)
      // also write "Err" on display
      binding.display.text = "Err"
    }
  }

  private fun deleteLastChar() {
    if (currentDisplay.isNotEmpty()) {
      var lastIndex = currentDisplay.length - 1
      if (currentDisplay[lastIndex] == ' ') {
        lastIndex-=2
      }
      currentDisplay = currentDisplay.substring(0, lastIndex)
      binding.display.text = currentDisplay
    }
  }

  private fun clearDisplay() {
    currentDisplay = ""
    binding.display.text = currentDisplay
  }

  private fun appendToDisplay(string: String) {
    currentDisplay += string
    binding.display.text = currentDisplay
  }
}