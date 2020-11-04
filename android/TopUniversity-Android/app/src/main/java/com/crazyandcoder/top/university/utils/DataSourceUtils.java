package com.crazyandcoder.top.university.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSourceUtils {

    private static HashMap<String, String> provinceHashMap = setProvince();
    private static HashMap<String, String> schoolTypeHashMap = setSchoolType();
    private static HashMap<String, String> banxueTypeHashMap = setBanxueType();

    public static List<String> getProvinceData() {
        List<String> province = new ArrayList<>();
        province.add("全部");
        for (String name : provinceHashMap.keySet()) {
            province.add(name);
        }

        return province;
    }

    public static String getProvinceId(String province) {
        return provinceHashMap.get(province);
    }

    private static HashMap<String, String> setProvince() {
        HashMap<String, String> province = new HashMap<>();
        province.put("黑龙江", "23");
        province.put("江苏", "32");
        province.put("河南", "41");
        province.put("上海", "31");
        province.put("辽宁", "21");
        province.put("福建", "35");
        province.put("山西", "14");
        province.put("湖南", "43");
        province.put("湖北", "42");
        province.put("广东", "44");
        province.put("浙江", "33");
        province.put("山东", "37");
        province.put("北京", "11");
        province.put("河北", "13");
        province.put("重庆", "50");
        province.put("陕西", "61");
        province.put("贵州", "52");
        province.put("四川", "51");
        province.put("广西", "45");
        province.put("海南", "46");
        province.put("吉林", "22");
        province.put("云南", "53");
        province.put("安徽", "34");
        province.put("天津", "12");
        province.put("江西", "36");
        province.put("内蒙古", "15");
        province.put("新疆", "65");
        province.put("西藏", "54");
        province.put("宁夏", "64");
        province.put("甘肃", "62");
        province.put("青海", "63");
        province.put("香港", "81");
        province.put("澳门", "82");
        return province;
    }


    public static List<String> getSchoolTypeData() {
        List<String> schoolType = new ArrayList<>();
        schoolType.add("全部");
        for (String name : schoolTypeHashMap.keySet()) {
            schoolType.add(name);
        }

        return schoolType;
    }

    public static String getSchoolTypeId(String province) {
        return schoolTypeHashMap.get(province);
    }

    private static HashMap<String, String> setSchoolType() {
        HashMap<String, String> schoolType = new HashMap<>();
        schoolType.put("理工类", "5001");
        schoolType.put("师范类", "5004");
        schoolType.put("医药类", "5003");
        schoolType.put("综合类", "5000");
        schoolType.put("财经类", "5006");
        schoolType.put("政法类", "5007");
        schoolType.put("体育类", "5008");
        schoolType.put("军事类", "5011");
        schoolType.put("语言类", "5005");
        schoolType.put("艺术类", "5009");
        schoolType.put("民族类", "5010");
        schoolType.put("农林类", "5002");
        schoolType.put("其他", "5012");
        return schoolType;
    }


    private static HashMap<String, String> setBanxueType() {
        HashMap<String, String> banxueType = new HashMap<>();
        banxueType.put("中外合作办学", "6003");
        banxueType.put("专科(高职)", "6001");
        banxueType.put("独立学院", "6002");
        banxueType.put("普通本科", "6000");
        banxueType.put("其他", "6007");
        return banxueType;
    }


    public static List<String> getBanxueTypeData() {
        List<String> schoolType = new ArrayList<>();
        schoolType.add("全部");
        for (String name : banxueTypeHashMap.keySet()) {
            schoolType.add(name);
        }

        return schoolType;
    }

    public static String getBanxueTypeId(String province) {
        return banxueTypeHashMap.get(province);
    }

    public static List<String> get985211Data() {
        List<String> _985List = new ArrayList<>();
        _985List.add("全部");
        _985List.add("985");
        _985List.add("211");

        return _985List;
    }
}
