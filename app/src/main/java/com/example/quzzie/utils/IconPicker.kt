package com.example.quzzie.utils

import com.example.quzzie.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.chat,
        R.drawable.dictionary,
        R.drawable.education,
        R.drawable.elearning,
        R.drawable.exam,
        R.drawable.online_learning,
        R.drawable.smartphone,
        R.drawable.website
    )
    var iconCurrentIndex = 0

    fun getIcon(): Int {
        iconCurrentIndex = (iconCurrentIndex + 1) % icons.size
        return icons[iconCurrentIndex]
    }
}