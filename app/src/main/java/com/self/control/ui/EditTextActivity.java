package com.self.control.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.self.control.R;
import com.self.control.base.BaseActivity;

import butterknife.BindView;

public class EditTextActivity extends BaseActivity {

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.et_decimal)
    EditText etDecimal;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_cursorr)
    EditText etCursorr;
    @BindView(R.id.et_listener)
    EditText etListener;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.btn_no:
                //设置不能够输入了，设置默认不能获取光标
                etInput.setFocusable(false);
                break;
            case R.id.btn_yes:
                //设置可以输入设置可以获取光标
                etInput.setFocusable(true);
                etInput.setFocusableInTouchMode(true);
                break;
        }
    }

    @Override
    public void setListener() {
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_edit_text;
    }

    @Override
    public void initView(View view) {
        //设置光标位置 ，可以设置在最前面 0， 和最后面 content.length()  最好配合监听一起用（如下面）
        etCursorr.setSelection(0);

        //输入监听
        etListener.addTextChangedListener(new TextWatcher() {
            @Override//s 更改前的文字、 start 有变动的字符串的序号、 count 被改变的长度（如果是增加，则为0）、 after 被修改的文字的长度（加了几个）如果是删除的话则为0
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("etListener1",s+" "+start+" "+count+" "+after);
            }

            @Override//s 更改后的文字、 start 有变动的字符串的序号、 before 被改变的长度（如果是增加，则为0）、 count 被修改的文字的长度（加了几个）如果是删除的话则为0
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("etListener2",s+" "+start+" "+before+" "+count);
            }

            @Override//s 修改后的文字
            public void afterTextChanged(Editable s) {
                Log.e("etListener3",s+"");
                etListener.setSelection(0);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

}
