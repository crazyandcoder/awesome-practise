package com.crazyandcoder.android.lib.base.widget.status.impl;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.crazyandcoder.android.lib.base.R;
import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * @ClassName: CrazyLoadingCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:47 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:47 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyLoadingCallback extends Callback {
        private AVLoadingIndicatorView avLoad;
    private boolean hasSet = false;

    @Override
    protected int onCreateView() {
        return R.layout.base_callback_crazy_loading;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        super.onViewCreate(context, view);
        if(view!=null && view.findViewById(R.id.avLoad)!=null){
            avLoad = (AVLoadingIndicatorView)view.findViewById(R.id.avLoad);
            if(!hasSet){
                hasSet = true;
                avLoad.setIndicator(INDICATORS[27]);
            }
            int color = Color.parseColor("#0A84FF");
            avLoad.setIndicatorColor(color);
        }
    }

    /**
     * 加载的类型
     */
    public static final String[] INDICATORS = new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };
}