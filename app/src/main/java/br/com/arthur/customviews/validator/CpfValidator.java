package br.com.arthur.customviews.validator;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

public class CpfValidator extends BaseValidator {

    private View container;
    private EditText input;

    public CpfValidator(EditText editText, View container) {
        super(editText);
        this.container = container;
        this.input = editText;
    }

    @Override
    public boolean validate() {
        boolean isValid = ValidationUtil.cpfValidate(input.getText().toString());
        if (!isValid) {
            ((TextInputLayout) container).setError("CPF inválido");
        } else {
            ((TextInputLayout) container).setErrorEnabled(false);
        }
        return isValid;
    }
}