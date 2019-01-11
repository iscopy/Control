package com.self.control;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.self.control.adapter.MainAdapter;
import com.self.control.base.BaseActivity;
import com.self.control.ui.ButtonActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_button)
    RecyclerView rvButton;
    private MainAdapter mainAdapter;
    private String[] name;

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
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        name = new String[]{"Button"};
        mainAdapter = new MainAdapter(getMContext(), name, i -> {
            switch (i){
                case 0:
                    startActivity(ButtonActivity.class);
                    break;
            }
        });
        rvButton.setLayoutManager(new GridLayoutManager(getMContext(), 1));
        rvButton.setAdapter(mainAdapter);
        rvButton.setNestedScrollingEnabled(false);
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
