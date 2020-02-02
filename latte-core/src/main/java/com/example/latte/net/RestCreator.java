package com.example.latte.net;

import com.example.latte.app.ConfigType;
import com.example.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 创建retrofit实例
 * 单例模式
 */
public class RestCreator {
    //因为params一直会使用，我们现在就实例化出来，然后进行使用，不要重复申请空间
    private static class ParamsHolder{
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
    public static final WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    //提供出去RestService
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());//获取到初始化传入的地址
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    //对OKhttp client的额外处理，进行okhttp初始化
    private static final class OKHttpHolder{
        private static final int TIME_OUT =60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    //对restService创建静态内部类
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
