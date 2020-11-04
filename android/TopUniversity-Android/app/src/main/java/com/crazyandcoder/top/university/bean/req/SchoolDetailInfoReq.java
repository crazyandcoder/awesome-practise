package com.crazyandcoder.top.university.bean.req;

public class SchoolDetailInfoReq {
    private String schoolId;

    public SchoolDetailInfoReq(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
