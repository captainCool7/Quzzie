package com.example.quzzie.models

data class Question(
    var answer: String = "",
    var description: String = "",
    var option1: String = "",
    var option2: String = "",
    var option3: String = "",
    var option4: String = "",
    var userAnswer: String = ""
)
