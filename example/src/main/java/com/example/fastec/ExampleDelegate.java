package com.example.fastec;

import android.os.Bundle;


import android.view.View;


import androidx.annotation.Nullable;

import com.example.latte.delegates.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    /**
     * 对每一个控件进行操作
     * @param savedInstanceState
     * @param rootView
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
