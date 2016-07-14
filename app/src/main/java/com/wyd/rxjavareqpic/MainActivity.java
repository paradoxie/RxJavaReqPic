package com.wyd.rxjavareqpic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.wyd.rxjavareqpic.presenter.MainPresenter;
import com.wyd.rxjavareqpic.viewinterface.MainViewInterface;
import com.wyd.rxjavareqpic.vo.ZhiHu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 本次视频主要讲解使用Retrofit访问知乎api获取数据，在RxJava中对数据进行处理，
 * 然后通过Imageloader对图片进行展示
 * 需要拿到的网络数据
 * {"text":"Davide Ragusa","img":"https:\/\/pic1.zhimg.com\/4dfa596eb7f3fe8a3c02f1d9a879271e.jpg"}
 */
public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private Unbinder unbinder;

    @BindView(R.id.text)
    TextView text;

    @BindView(R.id.img)
    ImageView img;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getData();
    }

    @Override
    public void showImg(ZhiHu zhiHu) {
        if (zhiHu == null) return;
        Logger.d(zhiHu.toString());
        text.setText(zhiHu.getText());
        ImageLoader.getInstance().displayImage(zhiHu.getImg(), img);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
}

