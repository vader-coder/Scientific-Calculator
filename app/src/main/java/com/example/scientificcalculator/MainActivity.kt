package com.example.scientificcalculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.*;
//import org.mariuszgromada.math.mxparser.Expression
import java.util.*


/**class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}*/

class MainActivity : AppCompatActivity() {
    companion object {
        var lastDecimal: Double = 0.0
        var justEvaluated: Boolean = true//whether expression was just evaluated
        var expressionStr : String = ""
        var ansStr : String = ""
        var calculationHistoryStack : Stack<calculation> = Stack<calculation>()

        //    constructor(expression: TextView, ans: TextView, btnEvaluate: View, btnClr: View) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//set content to be layout
        //set a variable in this class to track state.
        //Log10, Ln, Exp, Cos, Sin, Tan, ArcCos, ArcSin, ArcTan

        var twoOperandsOperationButtons = arrayOf<View>(btnPlus, btnMinus, btnMultiply, btnDivide)
        var twoOperandsOperationStrings = arrayOf<String>("+", "-", "*", "/")
        var stringsInsertedByButtons = arrayOf<String>(".",
            "log10(", "10^(", "ln(", "exp(", "cos(", "sin(", "tan(", "acos(", "asin(", "atan", "^2",
            "sqrt(", ")", "(", "!", "^("
        )
        var intButtons = arrayOf<View>(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        var oneOperandsOperationButtons = arrayOf<View>(btnDecimal, btnLog10, btn10Power,
            btnLn, btnExp, btnCos, btnSin, btnTan, btnArcCos, btnArcSin, btnArcTan, btnSquared, btnSqrt,
            btnClosedParenthesis, btnOpenParenthesis, btnFactorial, btnCarat)
        var index = 0
        //set up keys using operations with two operands so when pressed they will check if the expression
        //was just evaluated and input the string.
        var buttonSetter =  buttonSetter(expression, ans, btnEvaluate, btnClr)
        for (element in twoOperandsOperationButtons) {
            setTwoOperandOperation (element, expression, twoOperandsOperationStrings[index])
            //buttonSetter.setTwoOperandOperation(element, twoOperandsOperationStrings[index])
            index += 1
        }
        index = 0
        var button: View
        //set up buttons that insert strings into expression field to input strings when they are pressed
        //fun setButtonThatOnlyInsertsText(button: View, expression: TextView, buttonInput: String) {
        for (buttonStr in stringsInsertedByButtons) {
            button = oneOperandsOperationButtons[index]
            //buttonSetter.setButtonThatOnlyInsertsText(button, buttonStr)
            setButtonThatOnlyInsertsText(button, expression, buttonStr)
            index += 1
        }
        var buttonStr : String
        //set each integer button to enter an int into the expression field when it is pressed
        for (j in 0.rangeTo(9)) {//set up each integer button to enter an int
            button = intButtons[j]
            buttonStr = "${j}"
            setButtonThatOnlyInsertsText(button, expression, "${j}")
            //buttonSetter.setButtonThatOnlyInsertsText(button, buttonStr)
        }
        //buttonSetter.setEvaluate()
        //buttonSetter.setClr()
        btnEvaluate.setOnClickListener {
            //ansStr = expressionStr
            var e : Expression = Expression(expressionStr)
            ansStr = e.calculate().toString()
            ans.text = ansStr//expression.text.toString()//change to actual evaluation late
            calculationHistoryStack.push(calculation(expressionStr, ansStr))
            justEvaluated = true//only set to true when expression has just been evaluated.
        }
        //clear button pressed
        btnClr.setOnClickListener {
            ans.text = "0"//change to actual evaluation later
            expression.text = "0"
            expressionStr = ""
            ansStr = ""
            justEvaluated = true//only set to true when expression has just been evaluated.
        }
        //enable button to show last calculation
        btnShowLastCalculation.setOnClickListener {
            if (!calculationHistoryStack.empty()) {
                if (justEvaluated) {//if was just evaluated, want previous
                    calculationHistoryStack.pop()
                    justEvaluated = false
                }
                if (!calculationHistoryStack.empty()) {
                    var calculation: calculation = calculationHistoryStack.pop()
                    expressionStr = calculation.getExpression()
                    ansStr = calculation.getAns()
                    expression.text = expressionStr
                    ans.text = ansStr
                }
            }
        }
        /*var btnSetter : modeSetter = radMode(btnRad);
        btnSetter.setMode()
        btnSetter = degMode(btnDeg)
        btnSetter.setMode()
        object oriented version doesn't work, regular does.
        */

        //set to radians
        btnRad.setOnClickListener() {
            mXparser.setRadiansMode()
        }
        //set to degrees
        btnDeg.setOnClickListener() {
            mXparser.setDegreesMode()
        }
    }
}
