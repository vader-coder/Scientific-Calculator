package com.example.scientificcalculator

import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.ln

class buttonSetter {
    var justEvaluated : Boolean
    var expression : TextView
    var ans : TextView
    var btnEvaluate : View
    var btnClr : View
    var expressionStr : String
    constructor(expression: TextView, ans: TextView, btnEvaluate: View, btnClr: View) {
        this.justEvaluated = true
        this.expression = expression
        this.ans = ans
        this.btnEvaluate = btnEvaluate
        this.btnClr = btnClr
        this.expressionStr = ""
    }
    fun setButtonThatOnlyInsertsText(button: View, buttonInput: String) {
        button.setOnClickListener {
            if (!this.justEvaluated) {//default: not just evaluated
                this.expressionStr += buttonInput
                expression.text = expressionStr//expression.text.toString() + buttonInput
            }
            else {//if just evaluated, replace current text in expression with input string
                expressionStr = buttonInput
                expression.text = buttonInput
                this.justEvaluated = false//every time triggered & is true, need to now set to false
            }
        }
    }
    fun setTwoOperandOperation (button: View, operand: String) {
        if (!this.justEvaluated) {//default, not just evaluated
            button.setOnClickListener {
                expressionStr += operand
                expression.text = expressionStr//expression.text.toString() + operand
            }
        }
        else {//if pressed operand key after just evaluated, include answer in operation.
            button.setOnClickListener {
                expressionStr = ans.text.toString() + operand
                expression.text = expressionStr
                //expression.text = ans.text.toString() + operand
                this.justEvaluated = false
            }
        }
    }
    //btnEvaluate. could change this to object oriented by saving expression, ans as attributes.
    fun setEvaluate() {
        btnEvaluate.setOnClickListener {
            ans.text = expressionStr//expression.text.toString()//change to actual evaluation later
            justEvaluated = true//only set to true when expression has just been evaluated.
        }
    }
    //clear button pressed
    //btnClr
    fun setClr() {
        btnClr.setOnClickListener {
            ans.text = "0"//change to actual evaluation later
            expression.text = "0"
            expressionStr = ""
            this.justEvaluated = true//only set to true when expression has just been evaluated.
        }
    }
}