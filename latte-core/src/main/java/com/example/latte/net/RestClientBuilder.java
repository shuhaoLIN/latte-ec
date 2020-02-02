package com.example.latte.net;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private String mUrl;
    private static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;

    //限制只给restClient调用，使用包权限
    public RestClientBuilder() {
    }

    public final RestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> mParams) {
        PARAMS.putAll(mParams);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder request(IRequest mIRequest) {
        this.mIRequest = mIRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess mISuccess) {
        this.mISuccess = mISuccess;
        return this;
    }

    public final RestClientBuilder error(IError mIError) {
        this.mIError = mIError;
        return this;
    }

    public final RestClientBuilder failure(IFailure mIFailure) {
        this.mIFailure = mIFailure;
        return this;
    }

    //因为retrofit不允许空的params
//    private Map<String, Object> checkParams() {
//        if (mParams == null) {
//            mParams = new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIError, mIFailure, mBody);
    }
}
