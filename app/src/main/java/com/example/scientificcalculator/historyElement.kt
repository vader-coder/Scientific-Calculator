package com.example.scientificcalculator

class calculation {
    var ansStr : String
    var expressionStr : String
    constructor(expresion: String, ans: String) {
        expressionStr = expresion
        ansStr = ans
    }
    fun getExpression() : String {
        return expressionStr
    }
    fun getAns() : String {
        return ansStr
    }
}