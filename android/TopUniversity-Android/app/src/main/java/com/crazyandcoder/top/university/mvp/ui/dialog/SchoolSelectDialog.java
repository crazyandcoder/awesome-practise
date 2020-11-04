package com.crazyandcoder.top.university.mvp.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;
import com.crazyandcoder.android.lib.common.widget.dialog.AbsCrazyBaseDialog;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.utils.DataSourceUtils;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;

public class SchoolSelectDialog extends AbsCrazyBaseDialog {

    private MultiLineChooseLayout province_tag_flowLayout;
    private MultiLineChooseLayout school_type_flowLayout;
    private MultiLineChooseLayout school_985211_flowLayout;
    private MultiLineChooseLayout school_banxue_flowLayout;

    private FrameLayout closeFl;
    private TextView reset_tv;
    private TextView confirm_tv;

    private SchoolListReq schoolListReq;
    private OnSelectListener listener;

    public SchoolSelectDialog(@NonNull Context context, SchoolListReq schoolListReq, OnSelectListener listener) {
        super(context);
        this.schoolListReq = schoolListReq;
        this.listener = listener;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_school_list_select;
    }

    @Override
    protected void initView() {
        setBottomStyle();
        province_tag_flowLayout = findViewById(R.id.province_tag_flowLayout);
        school_985211_flowLayout = findViewById(R.id.school_985211_flowLayout);
        school_banxue_flowLayout = findViewById(R.id.school_banxue_flowLayout);
        school_type_flowLayout = findViewById(R.id.school_type_flowLayout);
        closeFl = findViewById(R.id.close_fl);
        reset_tv = findViewById(R.id.reset_tv);
        confirm_tv = findViewById(R.id.confirm_tv);


        setDefaultData();

        province_tag_flowLayout.setList(DataSourceUtils.getProvinceData());
        province_tag_flowLayout.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int i, String s) {
                schoolListReq.setProvinceName(s);
                schoolListReq.setProvinceId(DataSourceUtils.getProvinceId(s));
            }
        });

        school_985211_flowLayout.setList(DataSourceUtils.get985211Data());
        school_985211_flowLayout.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int i, String s) {
                if (i == 0) {
                    schoolListReq.set_985211(false);
                } else {
                    if (i == 1) {
                        schoolListReq.setF985("1");
                    } else {
                        schoolListReq.setF211("1");
                    }
                    schoolListReq.set_985211(true);
                }

            }
        });


        school_banxue_flowLayout.setList(DataSourceUtils.getBanxueTypeData());
        school_banxue_flowLayout.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int i, String s) {
                schoolListReq.setSchoolType(DataSourceUtils.getBanxueTypeId(s));
            }
        });


        school_type_flowLayout.setList(DataSourceUtils.getSchoolTypeData());
        school_type_flowLayout.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int i, String s) {
                schoolListReq.setType(DataSourceUtils.getSchoolTypeId(s));
            }
        });


    }

    private void setDefaultData() {

        if (!CrazyUtils.isEmpty(schoolListReq)) {
            if (schoolListReq.is_985211()) {
                if (!CrazyUtils.isEmpty(schoolListReq.getF211()) && schoolListReq.getF211().equals("1")) {
                    school_985211_flowLayout.setIndexItemSelected(2);
                }
                if (!CrazyUtils.isEmpty(schoolListReq.getF985()) && schoolListReq.getF985().equals("1")) {
                    school_985211_flowLayout.setIndexItemSelected(1);
                }
            } else {
                school_985211_flowLayout.setIndexItemSelected(0);
            }

            if (CrazyUtils.isEmpty(schoolListReq.getProvinceId()) || schoolListReq.getProvinceId().equals("0")) {
                province_tag_flowLayout.setIndexItemSelected(0);
            }


            if (CrazyUtils.isEmpty(schoolListReq.getType())) {
                school_type_flowLayout.setIndexItemSelected(0);
            }

            if (CrazyUtils.isEmpty(schoolListReq.getSchoolType())) {
                school_banxue_flowLayout.setIndexItemSelected(0);
            }

        }

    }

    @Override
    protected void initData() {


        reset_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                school_985211_flowLayout.cancelAllSelectedItems();
                school_type_flowLayout.cancelAllSelectedItems();
                province_tag_flowLayout.cancelAllSelectedItems();
                school_banxue_flowLayout.cancelAllSelectedItems();

                school_985211_flowLayout.setIndexItemSelected(0);
                school_type_flowLayout.setIndexItemSelected(0);
                province_tag_flowLayout.setIndexItemSelected(0);
                school_banxue_flowLayout.setIndexItemSelected(0);

                schoolListReq.setType("");
                schoolListReq.setSchoolType("");
                schoolListReq.setF211("");
                schoolListReq.setF985("");
                schoolListReq.setProvinceId("");

            }
        });

        confirm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onSelect(schoolListReq);
                }
                dismiss();
            }
        });
        closeFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public interface OnSelectListener {
        void onSelect(SchoolListReq schoolListReq);
    }
}
