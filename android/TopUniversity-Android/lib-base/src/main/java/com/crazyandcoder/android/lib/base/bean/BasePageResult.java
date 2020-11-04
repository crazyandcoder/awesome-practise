package com.crazyandcoder.android.lib.base.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BasePageResult
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 9:22 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 9:22 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BasePageResult<T> extends BaseResult<BasePageResult<T>> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int current;
    private int pages;
    private int size;
    private int total;
    private List<T> records;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
