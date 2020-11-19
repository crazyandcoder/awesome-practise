package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppUpdate extends BaseInfo implements Serializable {
    private Integer id;
    /**
     * app类型 1：android 2：ios 3：ipad
     */
    private int appType;
    /**
     * app最新版本
     */
    private int appLatestVer;

    /**
     * 更新类型 1：不更新,2：建议更新,3：强制更新,4：静默更新
     */
    private int updateType;

    /**
     * 更新包大小
     */
    private String appLatestSize;

    private String appPkg;
    /**
     * 更新说明
     */
    private String updateDesc;
    /**
     * 下载地址
     */
    private String appDownloadUrl;
    /**
     * app最新显示版本
     */
    private String appLatestShowVer;
    /**
     * app md5
     */
    private String appDownloadMd5;

    /**
     * 是否增量 暂时不用
     */
    private boolean delta;

    /**
     * 服务器端的原生返回数据（json）
     */
    private String originResData;

    /**
     * 是否强制更新
     */
    private boolean constraintUpdate;

    /**
     * 是否忽略当前版本更新
     */
    private boolean ignoreUpdate;

}
