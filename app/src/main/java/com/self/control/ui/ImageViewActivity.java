package com.self.control.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.self.control.R;
import com.self.control.base.BaseActivity;
import com.self.control.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewActivity extends BaseActivity {

    @BindView(R.id.iv_fore)
    ImageView ivFore;
    @BindView(R.id.iv_zoom)
    ImageView ivZoom;
    @BindView(R.id.iv_alpha)
    ImageView ivAlpha;
    @BindView(R.id.civ_circle)
    CircleImageView civCircle;
    @BindView(R.id.iv_scale_type)
    ImageView ivScaleType;

    int lastX = 0, lastY = 0;

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
        return R.layout.activity_image_view;
    }

    @SuppressLint({"NewApi", "ClickableViewAccessibility"})
    @Override
    public void initView(View view) {
        /**
         * 手指触摸事件,这里拖动只是顺带实现的  (这个拖动代码和 ScrollView 有些冲突，想要实现，需要单独写或者改进)
         * 主要是看 setForeground() 方法，和熟知的Background相反， foreground 是前景设置
         * 酷似蒙版效果
         */
        int wAll = getScreenWidth(this);
        int hAll = getScreenHeight(this);
        ivFore.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN://按下
                    ivFore.setForeground(getResources().getDrawable(R.color.image_fore));
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE://移动
                    int moveX = (int) (event.getRawX() - lastX);
                    int moveY = (int) (event.getRawY() - lastY);
                    int l, t, r, b;
                    int kuan = ivFore.getRight() - ivFore.getLeft();
                    int gao = ivFore.getBottom() - ivFore.getTop();
                    l = ivFore.getLeft() + moveX;
                    t = ivFore.getTop() + moveY;
                    r = ivFore.getRight() + moveX;
                    b = ivFore.getBottom() + moveY;

                    if (l < 0) {
                        l = 0;
                        r = kuan;
                    }
                    if (t < 0) {
                        t = 0;
                        b = gao;
                    }
                    if (r > wAll) {
                        r = wAll;
                        l = wAll - kuan;
                    }
                    if (b > hAll) {
                        b = hAll;
                        t = hAll - gao;
                    }

                    ivFore.layout(l, t, r, b);

                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_UP://松开
                    ivFore.setForeground(null);
                    break;
            }
            return true;
        });

        /**
         * 设置图片布局方式
         * ImageView 有一个布局大小，加载到这个布局的图片也会有自己的宽高比例大小
         * 布局的大小和图片的大小是很难一致的，那么图片该如何显示呢？
         * 这时，我们可以通过 setScaleType 方法来加载设置图片布局（同 android:scaleType）
         */
        ivScaleType.setScaleType(ImageView.ScaleType.CENTER);
        /**
         * 可选值如下：
         * 1、MATRIX / matrix：用矩阵的方式绘制，从ImageView的左上角开始绘制原图，不缩放图片， 超过ImageView部分作裁剪处理；
         * 2、CENTER / center：保持原图的大小，显示在ImageView的中心。当原图的尺寸大于ImageView的尺寸，超过部分裁剪处理；
         * 3、CENTER_CROP / centerCrop：保持横纵比缩放图片，直到完全覆盖ImageView为止（指的是ImageView的宽和高都要填满），原图超过ImageView的部分作裁剪处理；
         * 4、CENTER_INSIDE / centerInside：将图片的内容完整居中显示，通过按比例缩小原图尺寸的宽高等于或小于ImageView的宽高。如果原图的尺寸本身就小于ImageView的尺寸，则原图的尺寸不作任何处理，居中显示在ImageView；
         * 5、FIT_XY / fitXY：把原图宽高进行不保持原比例放缩，直到填充满ImageView为止；
         * 6、FIT_START / fitStart：把原图按比例放缩使之等于ImageView的宽高，缩放完成后将图片放在ImageView的左上角；
         * 7、FIT_CENTER / fitCenter：把原图按比例放缩使之等于ImageView的宽高使之居中显示，缩放后放于中间；
         * 8、FIT_END / fitEnd：把原图按比例放缩到ImageView的宽高，缩放完成后将图片放在ImageView的右下角。
         */

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
