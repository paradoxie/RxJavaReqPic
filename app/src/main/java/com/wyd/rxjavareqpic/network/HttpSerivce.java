package com.wyd.rxjavareqpic.network;

import com.wyd.rxjavareqpic.vo.ZhiHu;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by wangyd on 16/6/18.
 */
public interface HttpSerivce {
    //请求知乎的数据
//    @FormUrlEncoded
    @GET("/api/4/start-image/320*432")
//    public Observable<ZhiHu> getZhiHu(@FieldMap Map<String, String> params);
    public Observable<ZhiHu> getData();
}
