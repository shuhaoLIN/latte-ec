package com.example.fastec;


import android.os.Bundle;
import android.widget.Toast;

import com.example.latte.activities.ProxyActivity;
import com.example.latte.app.Latte;
import com.example.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //验证了我们的context已经传入到configuration中了（通过Example传入this）
//        Toast.makeText(Latte.getApplication(), "传入Context", Toast.LENGTH_SHORT).show();
//    }
}
