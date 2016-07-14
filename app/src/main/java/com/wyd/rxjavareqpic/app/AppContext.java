package com.wyd.rxjavareqpic.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.Logger;
import com.wyd.rxjavareqpic.R;
import com.wyd.rxjavareqpic.network.HttpSerivce;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by wangyd on 16/6/18.
 */
public class AppContext extends Application {
    public DisplayImageOptions options;

    public static HttpSerivce httpService;

//    http://news-at.zhihu.com/api/4/start-image/320*432
    String API ="http://news-at.zhihu.com/";

//    @FormUrlEncoded
//    @POST("/App/UserHandler.ashx")
//    public Observable<User> login(@FieldMap Map<String, String> params);

    @Override
    public void onCreate() {
        super.onCreate();
        //        logger初始化必须加入
        Logger.init("AppContext").methodCount(3);

        // 初始化imageload库
        initImageLoader();

        //初始化Retrofit
        initHttp();
    }


    private void initHttp() {
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        restAdapter.client().setConnectTimeout(10, TimeUnit.MINUTES);
        restAdapter.client().setReadTimeout(10, TimeUnit.MINUTES);
        restAdapter.client().setWriteTimeout(10, TimeUnit.MINUTES);
//        restAdapter.client().interceptors().add(new LoggingInterceptor());//拦截器
        // creating a service for adapter with our GET class
        httpService = restAdapter.create(HttpSerivce.class);
    }

    /**
     * 初始化ImageLoader initImageLoader: 使用imageload库必须加
     */
    public void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with
        // configuration.

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.wx)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.wx)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.wx)//设置图片加载/解码过程中错误时候显示的图片
                .delayBeforeLoading(100)//设置延时多少时间后开始下载
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的资源是否缓存在SD卡中
                .considerExifParams(true)// 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片以何种编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(500))//是否图片加载好后渐入的动画时间
                .build();
        ImageLoader.getInstance().init(config);
    }
}

