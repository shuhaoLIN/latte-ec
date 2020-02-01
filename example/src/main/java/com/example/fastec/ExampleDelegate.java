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

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
