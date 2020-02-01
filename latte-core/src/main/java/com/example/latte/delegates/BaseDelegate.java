package com.example.latte.delegates;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 不希望new出实例
 */
public abstract class BaseDelegate extends SwipeBackFragment {
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    /**
     * setLayout是一个int：就是id
     * 如果是一个view，那么就直接是根布局了
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);//绑定后实现相应的操作
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
