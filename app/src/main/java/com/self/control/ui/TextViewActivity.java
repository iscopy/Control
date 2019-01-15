package com.self.control.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.self.control.R;
import com.self.control.base.BaseActivity;

import butterknife.BindView;

public class TextViewActivity extends BaseActivity {

    @BindView(R.id.tv_color)
    TextView tvColor;
    @BindView(R.id.tv_big)
    TextView tvBig;
    @BindView(R.id.tv_image)
    TextView tvImage;
    @BindView(R.id.tv_background)
    TextView tvBackground;
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.tv_paragraph)
    TextView tvParagraph;
    @BindView(R.id.tv_aplication)
    TextView tvAplication;
    @BindView(R.id.tv_line_bottom_all)
    TextView tvLineBottomAll;
    @BindView(R.id.tv_in_line_all)
    TextView tvInLineAll;
    @BindView(R.id.tv_in_line_all_clear)
    TextView tvInLineAllClear;
    @BindView(R.id.tv_hickening)
    TextView tvHickening;

    private SpannableStringBuilder span;
    private SpannableString spannableString;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void setListener() {

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
        return R.layout.activity_text_view;
    }

    @SuppressLint("NewApi")
    @Override
    public void initView(View view) {
        //部分字体颜色
        spannableString = new SpannableString(tvColor.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 7, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 20, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.YELLOW), 22, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.young)), 24, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvColor.setText(spannableString);

        //部分字体放大
        spannableString = new SpannableString(tvBig.getText().toString());
        spannableString.setSpan(new AbsoluteSizeSpan(50), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvBig.setText(spannableString);

        //添加图片
        /**
         * 通过setCompoundDrawablesRelativeWithIntrinsicBounds 方法可以向 TextView 的上下左右添加图片,有两种添加方法:(默认居中)
         * setCompoundDrawablesRelativeWithIntrinsicBounds(@DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom)
         * setCompoundDrawablesRelativeWithIntrinsicBounds(@Nullable Drawable start,@Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom)
         */
        tvImage.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,//getResources().getDrawable(R.mipmap.ic_launcher_round)
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round);
        //文字中间添加图片
        spannableString = new SpannableString(tvImage.getText().toString());
        spannableString.setSpan(new ImageSpan(this, R.mipmap.ic_launcher_round), 7, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvImage.setText(spannableString);

        //局部布局背景
        spannableString = new SpannableString(tvBackground.getText().toString());
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 7, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvBackground.setText(spannableString);

        //划重点，删除划线
        spannableString = new SpannableString(tvLine.getText().toString());
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }

            @Override
            public void onClick(View widget) {
                //设置点击事件
                Toast.makeText(TextViewActivity.this, "重点", Toast.LENGTH_SHORT).show();
            }
        }, 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLine.setText(spannableString);
        tvLine.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮（背景）
        tvLine.setMovementMethod(LinkMovementMethod.getInstance());//设置响应点击事件

        //所有文字下划线
        tvLineBottomAll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvLineBottomAll.getPaint().setAntiAlias(true);//抗锯齿

        //所有文字中划线
        tvInLineAll.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvInLineAll.getPaint().setAntiAlias(true);

        //所有文字中划线（加清晰）
        tvInLineAllClear.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        //tvInLineAllClear.getPaint().setAntiAlias(true);

        //文字加粗
        tvHickening .getPaint().setFakeBoldText(true);

        //首行缩进(隐藏前面两个字)
        span = new SpannableStringBuilder("缩进" + tvParagraph.getText());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvParagraph.setText(span);

        //整体应用
        span = new SpannableStringBuilder("缩进" + tvAplication.getText());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvAplication.setText(span);
        spannableString = new SpannableString(tvAplication.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 15, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(55), 22, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ImageSpan(this, R.mipmap.ic_launcher_round), 7, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 27, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //ds.setColor(Color.RED);       //设置文件颜色
                ds.setUnderlineText(true);      //设置下划线
            }

            @Override
            public void onClick(View widget) {
                //设置点击事件
                Toast.makeText(TextViewActivity.this, "也是重点", Toast.LENGTH_SHORT).show();
            }
        }, tvAplication.length() - 5, tvAplication.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAplication.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮（背景）
        tvAplication.setMovementMethod(LinkMovementMethod.getInstance());//设置响应点击事件
        tvAplication.setText(spannableString);
        tvAplication.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.mipmap.ic_launcher_round,//getResources().getDrawable(R.mipmap.ic_launcher_round)
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round);
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
