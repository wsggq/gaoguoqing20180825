package com.example.ggq.gaoguoqing20180825.view;

import com.example.ggq.gaoguoqing20180825.bean.CartBean;

public interface CarView {
    void onSuccess(CartBean cartBean);
    void onError(String msg);
}
