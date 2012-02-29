package com.xtremelabs.robolectric.shadows;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.EditText;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;

/**
 * A shadow for EditText that provides support for listeners
 */
@SuppressWarnings({"UnusedDeclaration"})
@Implements(EditText.class)
public class ShadowEditText extends ShadowTextView {

    private int maxLength = Integer.MAX_VALUE;
    private int selectionStart = 0;
    private int selectionEnd = 0;

    public ShadowEditText() {
        focusable = true;
        focusableInTouchMode = true;
    }

    @Override
    public void applyAttributes() {
        super.applyAttributes();
        maxLength = attributeSet.getAttributeIntValue("android", "maxLength", Integer.MAX_VALUE);
    }

    @Override
    @Implementation(i18nSafe = true)
    public void setText(CharSequence str) {
        if ( !TextUtils.isEmpty(str) && str.length() > maxLength) {
            str = str.subSequence(0, maxLength);
        }
        super.setText(str);
    }

    @Override
    @Implementation
    public Editable getText() {
        CharSequence text = super.getText();
        if (!(text instanceof Editable)) {
            return new SpannableStringBuilder(text);
        }
        return (Editable) text;
    }

    @Implementation
    public void setSelection(int index) {
        setSelection(index, index);
    }

    @Implementation
    public void setSelection(int start, int end) {
        selectionStart = start;
        selectionEnd = end;
    }
    
    @Implementation
    public int getSelectionStart() {
        return selectionStart;
    }
    
    @Implementation
    public int getSelectionEnd() {
        return selectionEnd;
    }
}