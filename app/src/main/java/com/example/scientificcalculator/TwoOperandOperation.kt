package com.example.scientificcalculator
import com.example.scientificcalculator.MainActivity.Companion

import android.service.autofill.Validators.not
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun setTwoOperandOperation (button: View, expression: TextView, operand: String) {
    button.setOnClickListener {//every time listener called, needs to evaluate if statement.
        if (!Companion.justEvaluated) {//default, not just evaluated
            Companion.expressionStr += operand
            expression.text = Companion.expressionStr//expression.text.toString() + operand
        }
        else {//if pressed operand key after just evaluated, include answer in operation.
            Companion.expressionStr = Companion.ansStr + operand
            expression.text = Companion.expressionStr//ans.text.toString() + operand
            Companion.justEvaluated = false// no longer just evaluated
        }
    }

}