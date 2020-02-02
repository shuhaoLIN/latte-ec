package com.example.latte.net;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * 进行请求的具体实现类
 * 传入什么用什么：建造者模式alterdialog
 * 将建造者与宿主分开
 */
public class RestClient {
    //一次构建完毕，所以使用final
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    //回调，请求成功失败等信息
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
    }
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
}
