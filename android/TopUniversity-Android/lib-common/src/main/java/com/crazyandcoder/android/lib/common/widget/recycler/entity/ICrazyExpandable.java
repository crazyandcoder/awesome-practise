package com.crazyandcoder.android.lib.common.widget.recycler.entity;

import java.util.List;

public interface ICrazyExpandable<T> {
    boolean isExpanded();
    void setExpanded(boolean expanded);
    List<T> getSubItems();
    int getLevel();
}
