package com.crazyandcoder.top.university.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfessionImpress implements Parcelable {
    private String id;
    private String special_id;
    private String key_word;
    private String img_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecial_id() {
        return special_id;
    }

    public void setSpecial_id(String special_id) {
        this.special_id = special_id;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.special_id);
        dest.writeString(this.key_word);
        dest.writeString(this.img_url);
    }

    public ProfessionImpress() {
    }

    protected ProfessionImpress(Parcel in) {
        this.id = in.readString();
        this.special_id = in.readString();
        this.key_word = in.readString();
        this.img_url = in.readString();
    }

    public static final Parcelable.Creator<ProfessionImpress> CREATOR = new Parcelable.Creator<ProfessionImpress>() {
        @Override
        public ProfessionImpress createFromParcel(Parcel source) {
            return new ProfessionImpress(source);
        }

        @Override
        public ProfessionImpress[] newArray(int size) {
            return new ProfessionImpress[size];
        }
    };
}
