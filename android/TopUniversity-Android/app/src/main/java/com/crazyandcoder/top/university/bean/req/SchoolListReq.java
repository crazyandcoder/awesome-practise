package com.crazyandcoder.top.university.bean.req;

public class SchoolListReq {
    private String keyword;
    private String pageNo;
    private String pageSize;
    private String f211;
    private String f985;
    private String provinceId;
    private String schoolType;
    private String type;


    private boolean _985211 = false;
    private String provinceName;
    private String schoolTypeName;
    private String typeName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getSchoolTypeName() {
        return schoolTypeName;
    }

    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public boolean is_985211() {
        return _985211;
    }

    public void set_985211(boolean _985211) {
        this._985211 = _985211;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public SchoolListReq() {
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getF211() {
        return f211;
    }

    public void setF211(String f211) {
        this.f211 = f211;
    }

    public String getF985() {
        return f985;
    }

    public void setF985(String f985) {
        this.f985 = f985;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
