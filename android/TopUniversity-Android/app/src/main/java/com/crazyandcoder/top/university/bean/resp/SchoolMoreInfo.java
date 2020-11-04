package com.crazyandcoder.top.university.bean.resp;

public class SchoolMoreInfo {
    private int id;
    private String schoolId;
    private String typeKey;
    private String typeValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    @Override
    public String toString() {
        return "SchoolMoreInfoResp{" +
                "id=" + id +
                ", schoolId='" + schoolId + '\'' +
                ", typeKey='" + typeKey + '\'' +
                ", typeValue='" + typeValue + '\'' +
                '}';
    }
}
