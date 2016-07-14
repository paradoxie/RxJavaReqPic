package com.wyd.rxjavareqpic.data;

import com.wyd.rxjavareqpic.app.AppContext;
import com.wyd.rxjavareqpic.vo.ZhiHu;

import rx.Observable;

/**
 * Created by wangyd on 16/6/18.
 * 数据层
 */
public class DataManager {
    public static Observable<ZhiHu> getData() {
        //知乎数据网络获取
        return AppContext.httpService.getData();
    }
}
