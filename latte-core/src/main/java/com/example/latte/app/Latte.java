package com.example.latte.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

public class Latte {
    //在这里进行初始化
    //传入一个applicationcontext

    /**
     * 当我们调用init时，其实就是转入到配置中，
     * 然后调用下面的函数进行对Configu进行相应的配置
     * @param context
     * @return
     */
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }
    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
