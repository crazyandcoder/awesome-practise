package com.crazyandcoder.android.lib.common.utils.thread;

import android.util.Log;

import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName: CrazyTimerTask
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/28 2:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/28 2:09 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyCountDownTimer {

    private Timer timer;
    private TimerTask timerTask;
    private static CrazyCountDownTimer crazyCountDownTimer;

    private CrazyCountDownTimer() {

    }

    /**
     * 单例实现倒计时功能，便于控制释放倒计时资源
     *
     * @return
     */
    public static CrazyCountDownTimer getInstance() {
        if (crazyCountDownTimer == null) {
            synchronized (CrazyCountDownTimer.class) {
                if (crazyCountDownTimer == null) {
                    crazyCountDownTimer = new CrazyCountDownTimer();
                }
            }
        }
        return crazyCountDownTimer;
    }

    /**
     * 倒计时功能
     *
     * @param repeat        是否循环倒计时
     * @param countDownTime 倒计时总时长s
     * @param delay         延迟时间开始倒计时
     * @param callback      倒计时回调，提供每s的回调和结束倒计时为0的回调
     */
    public void start(boolean repeat, final int countDownTime, int delay, ICountDownCallback callback) {
        release();
        Log.e("crazyandcoder", "定时器 -----> start！");
        timer = new Timer();
        final int[] countDown = {countDownTime};
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (callback != null) {
                    Log.e("crazyandcoder", "定时器 -----> onTick " + countDown[0]);
                    callback.onTick((countDown[0]));

                    countDown[0]--;
                    if (countDown[0] < 0) {

                        //倒计时结束回调
                        if (callback != null) {
                            Log.e("crazyandcoder", "定时器 -----> onEndTick ！！！" );
                            callback.onEndTick();
                        }

                        //是否重复循环倒计时功能如果是的话，则重记倒计时开始时间
                        if (repeat) {
                            countDown[0] = countDownTime;
                        } else {
                            release();
                        }
                    }
                }
            }
        };
        if (timer != null) {
            timer.schedule(timerTask, delay, 1 * 1000);
        }
    }

    /**
     * 释放倒计时资源
     */
    public void release() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }

        Log.e("crazyandcoder", "定时器已清除！--release");

    }

    /**
     * 倒计时回调
     */
    public interface ICountDownCallback {

        /**
         * 每s倒计时回调
         *
         * @param time
         */
        void onTick(int time);

        /**
         * 一轮倒计时结束回调
         */
        void onEndTick();
    }

}
