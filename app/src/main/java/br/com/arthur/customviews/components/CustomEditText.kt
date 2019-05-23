package br.com.arthur.customviews.components

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.util.AttributeSet

class CustomEditText(context: Context?, attributeSet: AttributeSet) : TextInputEditText(context, attributeSet) {

    init {

    }

    fun getUpperText() = text.toString().toUpperCase()

}