package com.crazyandcoder.top.university.mvp.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @ClassName: BossMainTabViewPagerAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/13 7:04 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/13 7:04 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainTabViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public MainTabViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
