package com.self.control.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.self.control.R;
import com.self.control.base.BaseActivity;

import butterknife.BindView;

/**
 * 继承自定义的基类 BaseActivity
 */
public class ButtonActivity extends BaseActivity {

    /**
     * 这里是引入了  用来偷懒的依赖，相当于实例了下面的控件
     * implementation 'com.jakewharton:butterknife:8.8.1'
     * annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
     */
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.toggle_button)
    ToggleButton toggleButton;
    @BindView(R.id.image_button)
    ImageButton imageButton;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_woman)
    RadioButton rbWoman;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                /**
                 * 普通按钮的样式，点击事件都可以按照需求自己定义，点击事件分为四种
                 * 1、在xml里面添加监听属性：android:onClick="doClick"
                 * 2、匿名内部类实现，直接通过 setOnClickListener(new View.OnClickListener(){}  添加事件监听
                 * 3、自定义点击事件，将事件写在一个类里，接入 View.OnClickListener ，实现时 new 这个类就行了
                 * 4、直接在 Activity 上继承 View.OnClickListener ，由 Activity 实现
                 */
                Toast.makeText(getMContext(), "普通按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_button:
                /**
                 * 能够更方便的点击图片响应事件，可以删除背景按钮，自定义图片透明度、
                 * 可以通过android:src属性或setImageResource(int)方法指定图片
                 */
                Toast.makeText(getMContext(), "图片按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void setListener() {
        button.setOnClickListener(this);
        imageButton.setOnClickListener(this);
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
        return R.layout.activity_button;
    }

    @SuppressLint("NewApi")
    @Override
    public void initView(View view) {

        /**
         * ImageButton 设置背景透明度和图片透明度
         * 图片大小、背景颜色之类的，都可以自定义
         */
        imageButton.getBackground().setAlpha(100);//0~255透明度值
        imageButton.setImageAlpha(100);//0~255透明度值

        /**
         * 这里用到的是 //gradle-retrolambda配置
         * 可以在 build.gradle 看到引入 implementation 'me.tatarka:gradle-retrolambda:3.7.0'
         */
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //开关的两种状态
            if (isChecked) {
                toggleButton.setBackgroundResource(R.mipmap.open);
            } else {
                toggleButton.setBackgroundResource(R.mipmap.close);
            }
        });

        /**
         *RadioButton 单项选择，一般都通过 RadioGroup 控制，根据 checkedId 判断选择的是什么
         */
        rgSex.setOnCheckedChangeListener((group, checkedId) -> {
            //单选根据 RadioButton 的id判断选择的是哪个
            switch (checkedId){
                case R.id.rb_man:
                    Toast.makeText(getMContext(), "男", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_woman:
                    Toast.makeText(getMContext(), "女", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
