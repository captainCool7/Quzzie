package com.example.quzzie.utils

object ColorPicker {
    val colors = arrayOf("#E53935","#D81B60","#8E24AA","#5E35B1","#3949AB","#1E88E5","#039BE5","#00ACC1","#00897B")
    var colorCurrentIndex = 0

    fun getColor():String{
        colorCurrentIndex = (colorCurrentIndex+1)% colors.size
        return colors[colorCurrentIndex]
    }
}