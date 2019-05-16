package br.com.arthur.customviews.validator;

import android.view.View;
import android.widget.EditText;

public abstract class BaseValidator {

    private EditText input;

    BaseValidator(EditText input) {
        this.input = input;
        this.input.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validate();
            }
        });
    }

    public abstract boolean validate();

    View getInput() {
        return input;
    }
}