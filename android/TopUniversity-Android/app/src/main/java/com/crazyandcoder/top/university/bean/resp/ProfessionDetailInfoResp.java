package com.crazyandcoder.top.university.bean.resp;

import java.io.Serializable;

public class ProfessionDetailInfoResp implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String continue_exp;
    private String degree;
    private String do_what;
    private String e_status;
    private String heat;
    private String impress;
    private String is_what;
    private String job;
    private String jobdetail;
    private String jobrate;
    private String learn_what;
    private String level1;
    private String level1_name;
    private String level2;
    private String level3;
    private String limit_year;
    private String name;
    private String rate;
    private String sel_adv;
    private String status;
    private String type;
    private String type_detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContinue_exp() {
        return continue_exp;
    }

    public void setContinue_exp(String continue_exp) {
        this.continue_exp = continue_exp;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDo_what() {
        return do_what;
    }

    public void setDo_what(String do_what) {
        this.do_what = do_what;
    }

    public String getE_status() {
        return e_status;
    }

    public void setE_status(String e_status) {
        this.e_status = e_status;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getImpress() {
        return impress;
    }

    public void setImpress(String impress) {
        this.impress = impress;
    }

    public String getIs_what() {
        return is_what;
    }

    public void setIs_what(String is_what) {
        this.is_what = is_what;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobdetail() {
        return jobdetail;
    }

    public void setJobdetail(String jobdetail) {
        this.jobdetail = jobdetail;
    }

    public String getJobrate() {
        return jobrate;
    }

    public void setJobrate(String jobrate) {
        this.jobrate = jobrate;
    }

    public String getLearn_what() {
        return learn_what;
    }

    public void setLearn_what(String learn_what) {
        this.learn_what = learn_what;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel1_name() {
        return level1_name;
    }

    public void setLevel1_name(String level1_name) {
        this.level1_name = level1_name;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLimit_year() {
        return limit_year;
    }

    public void setLimit_year(String limit_year) {
        this.limit_year = limit_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSel_adv() {
        return sel_adv;
    }

    public void setSel_adv(String sel_adv) {
        this.sel_adv = sel_adv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_detail() {
        return type_detail;
    }

    public void setType_detail(String type_detail) {
        this.type_detail = type_detail;
    }

    @Override
    public String toString() {
        return "ProfessionDetailInfoResp{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", continue_exp='" + continue_exp + '\'' +
                ", degree='" + degree + '\'' +
                ", do_what='" + do_what + '\'' +
                ", e_status='" + e_status + '\'' +
                ", heat='" + heat + '\'' +
                ", impress='" + impress + '\'' +
                ", is_what='" + is_what + '\'' +
                ", job='" + job + '\'' +
                ", jobdetail='" + jobdetail + '\'' +
                ", jobrate='" + jobrate + '\'' +
                ", learn_what='" + learn_what + '\'' +
                ", level1='" + level1 + '\'' +
                ", level1_name='" + level1_name + '\'' +
                ", level2='" + level2 + '\'' +
                ", level3='" + level3 + '\'' +
                ", limit_year='" + limit_year + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", sel_adv='" + sel_adv + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", type_detail='" + type_detail + '\'' +
                '}';
    }
}
