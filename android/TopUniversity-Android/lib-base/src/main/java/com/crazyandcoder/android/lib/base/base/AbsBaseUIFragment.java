package com.crazyandcoder.android.lib.base.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.crazyandcoder.android.lib.base.R;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.mvp.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;
import com.crazyandcoder.android.lib.base.widget.status.core.LoadService;
import com.crazyandcoder.android.lib.base.widget.status.core.LoadSir;
import com.crazyandcoder.android.lib.base.widget.status.core.Transport;
import com.crazyandcoder.android.lib.base.widget.status.impl.CrazyConstant;
import com.crazyandcoder.android.lib.base.widget.toolbar.CustomToolbar;

import java.lang.reflect.Field;

/**
 * @ClassName: AbsBaseUiFragment
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/28 1:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/28 1:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsBaseUIFragment<P extends ICrazyPresenter> extends AbsCrazyBaseFragment<P> implements IBaseUIFragmentView {
    public CustomToolbar mToolbar;
    protected LinearLayout mLlContent;
    protected View rootView;
    private LoadService loadService;
    protected AbsBaseUIFragment baseuiFragment;

    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    protected boolean isPrepared = false;
    private boolean isViewCreated = false;
    private boolean isHiddenChanged = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseuiFragment = this;
    }

    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_base_ui;
    }

    @Override
    public int getContentViewId() {
        return R.layout.base_common_recycleview;
    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(initView(savedInstanceState), container, false);
        inflater.inflate(getContentViewId(), (LinearLayout) rootView.findViewById(R.id.base_content_ll), true);
        mToolbar = (CustomToolbar) rootView.findViewById(R.id.base_toolbar);
        mLlContent = (LinearLayout) rootView.findViewById(R.id.base_content_ll);
        if (getBackgroundRes() != 0) {
            mLlContent.setBackgroundResource(getBackgroundRes());
        }
        if (showToolbar()) {
            mToolbar.setVisibility(View.VISIBLE);
        } else {
            mToolbar.setVisibility(View.GONE);
        }
        findView(rootView);
        if (getLoadView() != null) {
            loadService = LoadSir.getDefault().register(getLoadView(), new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    onViewReload();
                }

            });
            showLoadSirView(CrazyConstant.LoadSir.SUCCESS);
        }
        isViewCreated = true;
        return rootView;
    }


    @Override
    public boolean showToolbar() {
        return false;
    }


    @Override
    public int getBackgroundRes() {
        return 0;
    }

    @Override
    public void setAppContentBackground(int res) {
        if (mLlContent != null && res != 0) {
            mLlContent.setBackgroundResource(res);
        }
    }

    @Override
    public void setAppNavBackGround(int res) {
        if (mToolbar != null && res != 0) {
            mToolbar.setBackgroundResource(res);
        }
    }

    @Override
    public void onNavLeftClick() {

    }

    @Override
    public View getLoadView() {
        return mLlContent;
    }

    @Override
    public void onViewReload() {

    }

    @Override
    public void showLoadSirView(String status) {
        if (loadService != null) {
            if (CrazyConstant.LoadSir.SUCCESS.equals(status)) {
                //加载完成
                loadService.showSuccess();
                return;
            }
            AppComponent appComponent = ((BaseApplication) getActivity().getApplication()).getAppComponent();
            if (appComponent.extras().containsKey(status) && appComponent.extras().get(status) instanceof Callback) {
                Callback callback = (Callback) appComponent.extras().get(status);
                loadService.setCallBack(callback.getClass(), new Transport() {
                    @Override
                    public void order(Context context, View view) {
                        onShowTransport(context, view, status);
                    }
                });
                loadService.showCallback(callback.getClass());
            }
        }
    }

    /**
     * 自定义错误，加载，识别页面数据
     *
     * @param context
     * @param view
     * @param status
     */
    @Override
    public void onShowTransport(Context context, View view, String status) {

    }

    //当没有使用ViewPage的时候，不会执行setUserVisibleHint方法，需要手动执行
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && !isHiddenChanged) {
            isHiddenChanged = true;
            initData();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    /***
     * 监听Fragment显示隐藏
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
            } else {
                onUserInvisible();
            }
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            initData();
        } else {
            isPrepared = true;
        }
    }

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected void onUserVisible() {
    }

    protected abstract void initData();

    protected void onUserInvisible() {

    }

    public void resume() {
        setUserVisibleHint(true);
    }

    public void pause() {
        setUserVisibleHint(false);
    }
}
