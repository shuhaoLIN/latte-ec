package com.example.latte.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.FrameLayout;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.example.latte.R;
import com.example.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作为fragment的容器
 * 所以fragment是不生效的
 * 因为以后会有一个主activity去使用该类，所以将其设置为抽象类
 */
public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            //第一次加载
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    /**
     * 因为我们是单activity，所以如果这个activity退出了，那么就回收
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
