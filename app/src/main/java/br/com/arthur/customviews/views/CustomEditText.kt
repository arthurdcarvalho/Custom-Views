package br.com.arthur.customviews.views

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo

class CustomEditText(context: Context?, attributeSet: AttributeSet) : TextInputEditText(context, attributeSet) {

    init {
        inputType = EditorInfo.TYPE_CLASS_NUMBER
    }

    fun getUpperText() = text.toString().toUpperCase()

}