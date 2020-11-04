package com.crazyandcoder.top.university.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CoreSuper implements Parcelable {


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public CoreSuper() {
    }

    protected CoreSuper(Parcel in) {
    }

}
