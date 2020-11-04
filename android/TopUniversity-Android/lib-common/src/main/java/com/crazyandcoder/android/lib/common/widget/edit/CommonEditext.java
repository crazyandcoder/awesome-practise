package com.crazyandcoder.android.lib.common.widget.edit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.crazyandcoder.android.lib.common.R;
import com.google.android.material.textfield.TextInputLayout;

public class CommonEditext extends RelativeLayout implements View.OnFocusChangeListener,
        TextWatcher {
    // 特殊下标位置
    private static final int PHONE_INDEX_3 = 3;
    private static final int PHONE_INDEX_4 = 4;
    private static final int PHONE_INDEX_8 = 8;
    private static final int PHONE_INDEX_9 = 9;

    private final String PASSWORD = "password";
    private final String PHONE = "phone";
    private final String CODE = "code";
    private EditText editText;
    private ImageView ivDeleteImage;
    private MyTextWatcher mTextWatcher;
    private String inputType = "defult";

    public CommonEditext(Context context) {
        this(context, null);
    }

    public CommonEditext(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_editext, this, true);
        editText = (EditText) view.findViewById(R.id.et_login_account);
        ivDeleteImage = (ImageView) view.findViewById(R.id.iv_delete_image);
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CommonEditext);
            if (attributes != null) {
                inputType = attributes.getString(R.styleable.CommonEditext_common_my_input_type);
                if (TextUtils.isEmpty(inputType)) {
                    inputType = "defult";
                }
                switch (inputType) {
                    case PASSWORD:
                        editText.setSingleLine(true);
                        editText.setMaxLines(1);
                        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter
                                (32)});
                        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                                .TYPE_TEXT_VARIATION_PASSWORD);

                        break;
                    case PHONE:
                        editText.setSingleLine(true);
                        editText.setMaxLines(1);
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter
                                (13)});
                        break;

                    case CODE:
                        editText.setSingleLine(true);
                        editText.setMaxLines(1);
                        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter
                                (10)});
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    default:
                        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter
                                (30)});
                        editText.setSingleLine(true);
                        editText.setMaxLines(1);
                        break;
                }
                String hint = attributes.getString(R.styleable.CommonEditext_common_my_hint);
                editText.setHint(hint);
            }

        }
        editText.setOnFocusChangeListener(this);
        editText.addTextChangedListener(this);
        ivDeleteImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mTextWatcher != null) {
            mTextWatcher.beforeTextChanged(charSequence, i, i1, i2);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String content = s.toString();
        if (!TextUtils.isEmpty(content)) {
            ivDeleteImage.setVisibility(VISIBLE);
        } else {
            content = "";
            ivDeleteImage.setVisibility(GONE);
        }

        if (TextUtils.equals(PHONE, inputType)) {
            if (s == null || s.length() == 0) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i != PHONE_INDEX_3 && i != PHONE_INDEX_8 && s.charAt(i) == ' ') {
                    continue;
                } else {
                    sb.append(s.charAt(i));
                    if ((sb.length() == PHONE_INDEX_4 || sb.length() == PHONE_INDEX_9) && sb.charAt
                            (sb.length() - 1) != ' ') {
                        sb.insert(sb.length() - 1, ' ');
                    }
                }
            }
            if (!sb.toString().equals(s.toString())) {
                int index = start + 1;
                if (sb.charAt(start) == ' ') {
                    if (before == 0) {
                        index++;
                    } else {
                        index--;
                    }
                } else {
                    if (before == 1) {
                        index--;
                    }
                }
                setText(sb.toString());
                editText.setSelection(index);
            }
        }

        if (mTextWatcher != null) {
            mTextWatcher.onTextChanged(s, start, before, count);
            mTextWatcher.onGetTextLength(content.length());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mTextWatcher != null) {
            mTextWatcher.afterTextChanged(editable);
        }
    }

    public String getText() {
        return editText.getText().toString().replaceAll(" ", "");
    }

    public void addTextChangedListener(MyTextWatcher textWatcher) {
        this.mTextWatcher = textWatcher;
    }

    public void setText(String text) {
        if (editText != null) {
            editText.setText(text);
        }
    }

    public void setHintText(String text) {
        if (editText != null) {
            editText.setHint(text);
        }
    }

    public static abstract class MyTextWatcher implements TextWatcher {
        protected abstract void onGetTextLength(int length);

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
