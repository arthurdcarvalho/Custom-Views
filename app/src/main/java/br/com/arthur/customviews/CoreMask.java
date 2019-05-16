package br.com.arthur.customviews;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class CoreMask {

    private MaskEvent maskEvent = null;

    public CoreMask() {

    }

    public CoreMask(MaskEvent maskEvent) {
        this.maskEvent = maskEvent;
    }

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll(",", "");
    }

    private static boolean isASign(char c) {
        return c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' ';
    }

    public TextWatcher insert(final MaskTypes maskType, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String oldString = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = CoreMask.unmask(s.toString());
                String coreMask = "";
                if (isUpdating) {
                    oldString = str;
                    isUpdating = false;
                    return;
                }

                int index = 0;
                for (int i = 0; i < maskType.getMaskFormat().length(); i++) {
                    char m = maskType.getMaskFormat().charAt(i);
                    if (m != '#') {
                        if (index == str.length() && str.length() < oldString.length()) {
                            continue;
                        }
                        coreMask += m;
                        continue;
                    }

                    try {
                        coreMask += str.charAt(index);
                    } catch (Exception e) {
                        break;
                    }

                    index++;
                }

                if (coreMask.length() > 0) {
                    char last_char = coreMask.charAt(coreMask.length() - 1);
                    boolean hadSign = false;
                    while (isASign(last_char) && str.length() == oldString.length()) {
                        coreMask = coreMask.substring(0, coreMask.length() - 1);
                        last_char = coreMask.charAt(coreMask.length() - 1);
                        hadSign = true;
                    }

                    if (coreMask.length() > 0 && hadSign) {
                        coreMask = coreMask.substring(0, coreMask.length() - 1);
                    }
                }

                isUpdating = true;
                ediTxt.setText(coreMask);
                ediTxt.setSelection(coreMask.length());

                if (maskEvent != null) {
                    if (s.length() == maskType.getMaskCount()) {
                        maskEvent.onMaskApplied(coreMask, str);
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    public interface MaskEvent {

        void onMaskApplied(String withMask, String withNoMask);

    }
}