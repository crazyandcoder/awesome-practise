package com.crazyandcoder.top.university.bean;

public class UpdateCoreSuper extends CoreSuper  {



    private int id;

    /**
     * 支持更新开始版本
     */
    private int appStartVer;

    /**
     * 支持更新结束版本
     */
    private int appEndVer;
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

    /**
     * 标题
     */
    private String updateTitle;

    /**
     * apk下载的存储目录
     */
    private String targetPath;

    /**
     * 隐藏当前更新框
     */
    private boolean hideDialog;

    private boolean showIgnoreVersion;
    private boolean dismissNotificationProgress;
    private boolean onlyWifi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppStartVer() {
        return appStartVer;
    }

    public void setAppStartVer(int appStartVer) {
        this.appStartVer = appStartVer;
    }

    public int getAppEndVer() {
        return appEndVer;
    }

    public void setAppEndVer(int appEndVer) {
        this.appEndVer = appEndVer;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getAppLatestVer() {
        return appLatestVer;
    }

    public void setAppLatestVer(int appLatestVer) {
        this.appLatestVer = appLatestVer;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public String getAppLatestSize() {
        return appLatestSize;
    }

    public void setAppLatestSize(String appLatestSize) {
        this.appLatestSize = appLatestSize;
    }

    public String getAppPkg() {
        return appPkg;
    }

    public void setAppPkg(String appPkg) {
        this.appPkg = appPkg;
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    public String getAppLatestShowVer() {
        return appLatestShowVer;
    }

    public void setAppLatestShowVer(String appLatestShowVer) {
        this.appLatestShowVer = appLatestShowVer;
    }

    public String getAppDownloadMd5() {
        return appDownloadMd5;
    }

    public void setAppDownloadMd5(String appDownloadMd5) {
        this.appDownloadMd5 = appDownloadMd5;
    }

    public boolean isDelta() {
        return delta;
    }

    public void setDelta(boolean delta) {
        this.delta = delta;
    }

    public String getOriginResData() {
        return originResData;
    }

    public void setOriginResData(String originResData) {
        this.originResData = originResData;
    }

    public boolean isConstraintUpdate() {
        return constraintUpdate;
    }

    public void setConstraintUpdate(boolean constraintUpdate) {
        this.constraintUpdate = constraintUpdate;
    }

    public boolean isIgnoreUpdate() {
        return ignoreUpdate;
    }

    public void setIgnoreUpdate(boolean ignoreUpdate) {
        this.ignoreUpdate = ignoreUpdate;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public boolean isHideDialog() {
        return hideDialog;
    }

    public void setHideDialog(boolean hideDialog) {
        this.hideDialog = hideDialog;
    }

    public boolean isShowIgnoreVersion() {
        return showIgnoreVersion;
    }

    public void setShowIgnoreVersion(boolean showIgnoreVersion) {
        this.showIgnoreVersion = showIgnoreVersion;
    }

    public boolean isDismissNotificationProgress() {
        return dismissNotificationProgress;
    }

    public void setDismissNotificationProgress(boolean dismissNotificationProgress) {
        this.dismissNotificationProgress = dismissNotificationProgress;
    }

    public boolean isOnlyWifi() {
        return onlyWifi;
    }

    public void setOnlyWifi(boolean onlyWifi) {
        this.onlyWifi = onlyWifi;
    }
}
