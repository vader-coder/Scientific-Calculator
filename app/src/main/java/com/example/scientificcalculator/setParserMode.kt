package com.example.scientificcalculator

import android.view.View
import org.mariuszgromada.math.mxparser.mXparser

fun setMode(button : View, setFunc: () -> Unit) {
    button.setOnClickListener {
        setFunc()
    }
}

open class modeSetter {
    var button : View
    constructor(button: View) {
        this.button = button
    }
    open fun set() {
        button.setOnClickListener {
            setMode()
        }
    }
    open fun setMode() {

    }

}
//set to radian, degree mode.
class radMode(button: View) : modeSetter(button) {
    override fun setMode() {
        mXparser.setRadiansMode()
    }
}
class degMode(button: View) : modeSetter(button) {
    override fun setMode() {
        mXparser.setDegreesMode()
    }
}