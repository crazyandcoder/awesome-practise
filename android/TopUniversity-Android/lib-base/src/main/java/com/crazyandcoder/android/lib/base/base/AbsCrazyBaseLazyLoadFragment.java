package com.crazyandcoder.android.lib.base.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazyandcoder.android.lib.base.R;
import com.crazyandcoder.android.lib.base.common.Unused;
import com.crazyandcoder.android.lib.base.mvp.ICrazyPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbsCrazyBaseLazyLoadFragment<P extends ICrazyPresenter> extends AbsCrazyBaseFragment<P> {

    @Inject
    Unused mUnused;

    // 界面是否已创建完成
    private boolean isViewCreated;

    // 是否对用户可见
    private boolean isVisibleToUser;

    // 数据是否已请求
    private boolean isDataLoaded;

    /**
     * 第一次可见时触发调用,此处实现具体的数据请求逻辑
     */
    protected abstract void lazyLoadData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    /**
     * 保证在initData后触发
     */
    @Override
    public void onResume() {
        super.onResume();
        isViewCreated = true;
        tryLoadData();
    }

    /**
     * ViewPager场景下，判断父fragment是否可见
     */
    private boolean isParentVisible() {
        Fragment fragment = getParentFragment();
        return fragment == null || (fragment instanceof AbsCrazyBaseLazyLoadFragment && ((AbsCrazyBaseLazyLoadFragment) fragment).isVisibleToUser);
    }

    /**
     * ViewPager场景下，当前fragment可见时，如果其子fragment也可见，则让子fragment请求数据
     */
    private void dispatchParentVisibleState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof AbsCrazyBaseLazyLoadFragment && ((AbsCrazyBaseLazyLoadFragment) child).isVisibleToUser) {
                ((AbsCrazyBaseLazyLoadFragment) child).tryLoadData();
            }
        }
    }

    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && isParentVisible() && !isDataLoaded) {
            lazyLoadData();
            isDataLoaded = true;
            //通知子Fragment请求数据
            dispatchParentVisibleState();
        }
    }
}
