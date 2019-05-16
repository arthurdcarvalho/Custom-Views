package br.com.arthur.customviews.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import br.com.arthur.customviews.CoreMask
import br.com.arthur.customviews.CoreMask.MaskEvent
import br.com.arthur.customviews.MaskTypes
import br.com.arthur.customviews.R
import br.com.arthur.customviews.validator.CpfValidator

class CompostCustomView(context: Context, attributesSet: AttributeSet) : ConstraintLayout(context, attributesSet) {

    private var view: View = View.inflate(context, R.layout.custom_edit_text, this)

    private var customEditText: CustomEditText
    private var textInputLayout: TextInputLayout
    private var progressBar: ProgressBar

    private var cpfValidator: CpfValidator? = null

    private var mask: CoreMask

    init {
        customEditText = view.findViewById(R.id.textCustom)
        textInputLayout = view.findViewById(R.id.textInputLayout)
        progressBar = view.findViewById(R.id.testProgressBar)

        cpfValidator = CpfValidator(customEditText, textInputLayout)

        customEditText.setOnFocusChangeListener { _: View, b: Boolean ->
            if (b) progressBar.visibility = View.VISIBLE
        }

        mask = CoreMask(MaskEvent { _, _ ->
            if (cpfValidator!!.validate()) {
                progressBar.visibility = View.INVISIBLE
            } else {
                Toast.makeText(context, "CPF inválido", Toast.LENGTH_SHORT).show()
            }
        })

        val cpfMask = mask.insert(MaskTypes.CPF, customEditText)
        customEditText.addTextChangedListener(cpfMask)

    }
}