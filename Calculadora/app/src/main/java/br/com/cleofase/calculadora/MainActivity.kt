package br.com.cleofase.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var userIsInMiddleOfTyping = false
    private val hasDecimalDot: Boolean
        get() = textViewDisplay.text.indexOf(".") > 0
    private var calculatorEngine = CalculatorBrain()
    private var displayValue: Double
        get() = textViewDisplay.text.toString().toDouble()
        set(newValue) {
            textViewDisplay.text = newValue.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewZero.setOnClickListener { onCliqueDigit(textViewZero.text.toString()) }
        textViewOne.setOnClickListener { onCliqueDigit(textViewOne.text.toString()) }
        textViewTwo.setOnClickListener { onCliqueDigit(textViewTwo.text.toString()) }
        textViewThree.setOnClickListener { onCliqueDigit(textViewThree.text.toString()) }
        textViewFour.setOnClickListener { onCliqueDigit(textViewFour.text.toString()) }
        textViewFive.setOnClickListener { onCliqueDigit(textViewFive.text.toString()) }
        textViewSix.setOnClickListener { onCliqueDigit(textViewSix.text.toString()) }
        textViewSeven.setOnClickListener { onCliqueDigit(textViewSeven.text.toString()) }
        textViewEight.setOnClickListener { onCliqueDigit(textViewEight.text.toString()) }
        textViewNine.setOnClickListener { onCliqueDigit(textViewNine.text.toString()) }
        textViewDecimal.setOnClickListener { onCliqueDecimalDot(textViewDecimal.text.toString()) }
        textViewSum.setOnClickListener { onCliqueOperation(textViewSum.text.toString()) }
        textViewSubtract.setOnClickListener { onCliqueOperation(textViewSubtract.text.toString()) }
        textViewMultiply.setOnClickListener { onCliqueOperation(textViewMultiply.text.toString()) }
        textViewDivide.setOnClickListener { onCliqueOperation(textViewDivide.text.toString()) }
        textViewPercent.setOnClickListener { onCliqueOperation(textViewPercent.text.toString()) }
        textViewResult.setOnClickListener { onCliqueOperation(textViewResult.text.toString()) }
        textViewClear.setOnClickListener { onCliqueOperation(textViewClear.text.toString()) }
        textViewDel.setOnClickListener { onCliqueDel(textViewDel.text.toString()) }
    }

    private fun onCliqueKey(symbol: String) {
        Toast.makeText(this,symbol,Toast.LENGTH_SHORT).show()
    }

    private fun onCliqueDigit(digit: String) {
        val currentValue = textViewDisplay.text.toString()
        textViewDisplay.text = if (userIsInMiddleOfTyping) {currentValue + digit} else {userIsInMiddleOfTyping = true; digit}
    }

    private fun onCliqueDecimalDot(symbol: String) {
        if (!hasDecimalDot && userIsInMiddleOfTyping) {
            val currentValue = textViewDisplay.text.toString()
            textViewDisplay.text = currentValue + symbol
        }
    }

    private fun onCliqueOperation(symbol: String) {
        calculatorEngine.setOperand(displayValue)
        userIsInMiddleOfTyping = false
        calculatorEngine.performOperation(symbol)
        displayValue = calculatorEngine.result
    }

    private fun onCliqueDel(symbol: String) {
        if (userIsInMiddleOfTyping) {
            textViewDisplay.text = if (textViewDisplay.text.toString().length > 1) {textViewDisplay.text.dropLast(1) } else {userIsInMiddleOfTyping = false; "0"}
        }
    }
}
