package com.example.ggq.gaoguoqing20180825.model;

import android.os.Handler;
import com.example.ggq.gaoguoqing20180825.bean.CartBean;
import com.example.ggq.gaoguoqing20180825.utils.OKHttpUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import okhttp3.Response;

public class CarModel {
    private Handler handler = new Handler();
    public void getCar(String url, HashMap<String,String> params, final CartCallback cartCallback){
        OKHttpUtil.getInstance().post(url, params, new OKHttpUtil.RequestCallback() {
            @Override
            public void onSuccess(Response response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    try {
                        String result = response.body().string();
                        parseResult(result,cartCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(IOException e) {
                cartCallback.onError("数据请求失败,请稍后再试");
            }
        });
    }

    private void parseResult(String result, final CartCallback cartCallback) {
        final CartBean cartBean = new Gson().fromJson(result, CartBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                cartCallback.onSuccess(cartBean);
            }
        });
    }

    public interface CartCallback{
        void onSuccess(CartBean cartBean);
        void onError(String msg);
    }
}
