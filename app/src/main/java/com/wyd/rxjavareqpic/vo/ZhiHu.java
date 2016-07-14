package com.wyd.rxjavareqpic.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangyd on 16/6/18.
 * 知乎数据实体对象
 */
public class ZhiHu implements Parcelable{

    /**
     * text : Davide Ragusa
     * img : https://pic1.zhimg.com/4dfa596eb7f3fe8a3c02f1d9a879271e.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ZhiHu{" +
                "img='" + img + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.img);
    }

    public ZhiHu() {
    }

    protected ZhiHu(Parcel in) {
        this.text = in.readString();
        this.img = in.readString();
    }

    public static final Creator<ZhiHu> CREATOR = new Creator<ZhiHu>() {
        public ZhiHu createFromParcel(Parcel source) {
            return new ZhiHu(source);
        }

        public ZhiHu[] newArray(int size) {
            return new ZhiHu[size];
        }
    };
}
