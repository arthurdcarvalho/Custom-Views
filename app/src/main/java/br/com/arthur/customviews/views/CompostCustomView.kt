package br.com.arthur.customviews.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import br.com.arthur.customviews.CoreMask
import br.com.arthur.customviews.CoreMask.MaskEvent
import br.com.arthur.customviews.MaskTypes
import br.com.arthur.customviews.R
import br.com.arthur.customviews.validator.CpfValidator

class CompostCustomView(context: Context, private val attributesSet: AttributeSet) :
    ConstraintLayout(context, attributesSet) {

    private var view: View = View.inflate(context, R.layout.custom_compost_view, this)

    private lateinit var input: CustomEditText
    private lateinit var containerForInput: TextInputLayout

    private lateinit var inputPassword: CustomEditText
    private lateinit var containerPassword: TextInputLayout

    private lateinit var buttonSend: Button

    private lateinit var progressBar: ProgressBar

    private lateinit var mask: CoreMask

    private var maskWatcher: TextWatcher? = null

    private lateinit var inputWithNoMask: String
    private lateinit var inputWithMask: String

    private var hasButton: Boolean = false
    private var maskFormat: Int? = null

    init {
        initComponents()
        captureAttributes()
        constructView()

        input.setOnFocusChangeListener { _: View, b: Boolean ->
            if (b) progressBar.visibility = View.VISIBLE
        }
    }

    private fun initComponents() {
        input = view.findViewById(R.id.input)
        containerForInput = view.findViewById(R.id.containerForInput)
        inputPassword = view.findViewById(R.id.inputPassword)
        containerPassword = view.findViewById(R.id.containerPassword)
        buttonSend = view.findViewById(R.id.buttonSend)
        progressBar = view.findViewById(R.id.testProgressBar)
    }

    private fun captureAttributes() {
        context.theme.obtainStyledAttributes(
            attributesSet,
            R.styleable.CompostCustomView,
            0,
            0
        ).apply {
            try {
                hasButton = getBoolean(R.styleable.CompostCustomView_hasSendButton, false)
                maskFormat = getInt(R.styleable.CompostCustomView_maskType, 0)
            } finally {
                recycle()
            }
        }
    }

    private fun constructView() {
        if (hasButton) buttonSend.visibility = View.VISIBLE else buttonSend.visibility = View.GONE
        when (maskFormat) {

            MaskTypes.CPF.id -> {
                containerForInput.hint = MaskTypes.CPF.maskName

                val cpfValidator = CpfValidator(input, containerForInput)

                mask = CoreMask(MaskEvent { withMask, withNoMask ->
                    if (cpfValidator.validate()) {
                        progressBar.visibility = View.INVISIBLE
                        containerPassword.visibility = View.VISIBLE
                        buttonSend.visibility = View.VISIBLE
                        inputWithNoMask = withNoMask
                        inputWithMask = withMask
                    } else {
                        containerForInput.error = "CPF inválido"
                    }
                })


                maskWatcher = mask.insert(MaskTypes.CPF, input)
                input.addTextChangedListener(maskWatcher)
            }

            MaskTypes.DATE.id -> {

                buttonSend.isEnabled = false

                containerForInput.hint = MaskTypes.DATE.maskName

                mask = CoreMask(MaskEvent { withMask, withNoMask ->
                    inputWithNoMask = withNoMask
                    inputWithMask = withMask
                    buttonSend.isEnabled = true
                })

                maskWatcher = mask.insert(MaskTypes.DATE, input)
                input.addTextChangedListener(maskWatcher)

            }

        }
    }

    fun getInputWithNoMask() = inputWithNoMask
    fun getInputWithMask() = inputWithMask

    fun setButtonSendListener(listener: OnClickListener) = buttonSend.setOnClickListener(listener)

}