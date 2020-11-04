package com.crazyandcoder.android.lib.common.widget.banner.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.common.widget.banner.config.IndicatorConfig;
import com.crazyandcoder.android.lib.common.widget.banner.listener.OnPageChangeListener;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
