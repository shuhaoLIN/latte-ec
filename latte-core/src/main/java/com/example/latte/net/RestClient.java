package com.example.latte.net;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.net.callback.RequestCallbacks;

import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

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

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        //开始请求
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback()); //该方法是封装好的异步的，所以不用自己进行起一个线程（excute就需要）
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                ERROR,
                FAILURE
        );
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
}
