package com.wyd.rxjavareqpic.presenter;

import com.orhanobut.logger.Logger;
import com.wyd.rxjavareqpic.data.DataManager;
import com.wyd.rxjavareqpic.viewinterface.MainViewInterface;
import com.wyd.rxjavareqpic.vo.ZhiHu;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangyd on 16/6/18.
 */
public class MainPresenter {
    private MainViewInterface mainViewInterface;

    public MainPresenter(MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }

//    new Thread(网络访问).start();

//    被观察着.订阅.观察者
    public void getData(){
        DataManager.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<ZhiHu>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.toString());
            }

            @Override
            public void onNext(ZhiHu zhiHu) {

                mainViewInterface.showImg(zhiHu);
            }
        });
    }
}
