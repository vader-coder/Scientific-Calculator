package com.example.scientificcalculator
import com.example.scientificcalculator.MainActivity.Companion

import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.ln

fun setButtonThatOnlyInsertsText(button: View, expression: TextView, buttonInput: String) {
    button.setOnClickListener {
        if (!Companion.justEvaluated) {//default: not just evaluated
            Companion.expressionStr += buttonInput
            expression.text = Companion.expressionStr //expression.text.toString() + buttonInput
        }
        else {//if just evaluated, replace current text in expression with input string
            expression.text = buttonInput
            Companion.expressionStr = buttonInput
            Companion.justEvaluated = false
        }
    }
}