package com.kekemei.kekemei.utils.callback;

public interface ProviderCallback {

    /**
     * 请求网络开始前，UI线程
     */
    void onStart();

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    void onSuccess(Object response);

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    void onError(Object response);

    /**
     * 请求网络结束后，UI线程
     */
    void onFinish();

}
