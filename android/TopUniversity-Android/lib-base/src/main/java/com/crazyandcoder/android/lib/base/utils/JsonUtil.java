package com.crazyandcoder.android.lib.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class JsonUtil {
    static Gson gson = null;
    static Gson commonGson = null;

    static Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
            gson = builder.create();

        }
        return gson;
    }

    static Gson getCommonGson() {
        if (commonGson == null) {
            commonGson = new Gson();
        }
        return commonGson;
    }

    public static Gson getGson2() {
        return getGson();
    }

    public static String getObject2Json(Object object) {
        String tempStr;
        tempStr = getGson().toJson(object);
        return tempStr;
    }

    public static String getJsonObjectByKey(Object Jsondata, String key) {
        String result = null;
        try {
            if (Jsondata == null) {
                return result;
            }
            JSONObject jsonObject = new JSONObject(Jsondata.toString());
            result = jsonObject.optString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getJsonObjectInt(Object Jsondata, String key) {
        int result = 0;
        try {
            if (Jsondata == null) {
                return result;
            }
            JSONObject jsonObject = new JSONObject(Jsondata.toString());
            result = jsonObject.optInt(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONArray getJsonArray(Object jsondata, String key) {
        JSONArray result = null;
        try {
            if (jsondata == null) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(jsondata.toString());
            result = jsonObject.optJSONArray(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject getJsonObject(Object jsondata, String key) {
        JSONObject result = null;
        try {
            if (jsondata == null) {
                return null;
            }
            JSONObject jsonObject = new JSONObject(jsondata.toString());
            result = jsonObject.optJSONObject(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getString(Object jsondata, String key) {
        String result = null;
        try {
            if (jsondata == null) {
                return result;
            }
            JSONObject jsonObject = new JSONObject(jsondata.toString());
            result = jsonObject.optString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean getBoolean(Object jsondata, String key) {
        boolean result = false;
        try {
            if (jsondata == null) {
                return result;
            }
            JSONObject jsonObject = new JSONObject(jsondata.toString());
            result = jsonObject.optBoolean(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getEndMark(Object Jsondata) {
        return getValueByKey(Jsondata, "endMark");
    }

    public static int getValueByKey(Object Jsondata, String key) {
        int result = 0;
        try {
            if (Jsondata == null) {
                return 0;
            }
            JSONObject jsonObject = new JSONObject(Jsondata.toString());
            result = jsonObject.optInt(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getValueByResults(Object Jsondata, String key) {
        int result = 0;
        try {
            if (Jsondata == null) {
                return 0;
            }
            JSONObject jsonObject = new JSONObject(Jsondata.toString());
            JSONObject resultsObject = getJsonObject(jsonObject, "results");
            result = resultsObject.optInt(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getStringByResults(Object Jsondata, String key) {
        String result = "";
        try {
            if (Jsondata == null) {
                return "";
            }
            JSONObject jsonObject = new JSONObject(Jsondata.toString());
            JSONObject resultsObject = getJsonObject(jsonObject, "results");
            result = resultsObject.optString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double getDoubleByKey(Object Jsondata, String key) {
        double result = 0.0;
        try {
            if (Jsondata == null) {
                return 0.0;
            }
            JSONObject jsonObject = new JSONObject(Jsondata.toString());
            result = jsonObject.optDouble(key);
            result = AccurateUtil.getDouble1Bit(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //解析ExtraInfo by hj
    public static <T> T getExtarInfoData(Object jsondata, Class<T> cls, String key) throws
            Exception {
        JSONObject extraInfo = JsonUtil.getJsonObject(jsondata, "extraInfo");
        return getResultData(extraInfo, cls, key);
    }

    //解析extraninfo的单个boolean值
    public static boolean getExtraInfoBoolean(Object resultData, String key) {
        boolean result = false;
        try {
            JSONObject extraInfo = JsonUtil.getJsonObject(resultData, "extraInfo");
            if (extraInfo != null) {
                if (!extraInfo.isNull(key)) {
                    result = extraInfo.getBoolean(key);
                    return result;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    //普通的类解析
    public static <T> T getResultData(Object jsondata, Class<T> cls) {
        return getResultData(jsondata, cls, "results");
    } //普通的类解析

    public static <T> T getCommonResultData(Object jsondata, Class<T> cls) {
        return getCommonResultData(jsondata, cls, "results");
    }

    /***
     * 解析全部
     *
     * @param jsondata
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getAllData(Object jsondata, Class<T> cls) {
        if (jsondata == null) {
            return null;
        } else {
            return getGson().fromJson(jsondata.toString(), cls);
        }
    }

    //字符串转换成list
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    public static <T> T getResultData(Object jsondata, Class<T> cls, String key) {
        T t = null;
        try {
            String js = getString(jsondata, key);
            t = getGson().fromJson(js, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T getCommonResultData(Object jsondata, Class<T> cls, String key) {
        T t = null;
        try {
            String js = getString(jsondata, key);
            t = getCommonGson().fromJson(js, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    //list的类解析
    public static <T> List<T> getResultListData(Object jsondata, Class<T> cls, List<T> list) {

        return getResultListData(jsondata, cls, "results", list);
    }

    /**
     * list有缓存的类解析
     *
     * @param jsondata
     * @param cls
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> getResultListData(Object jsondata, Class<T> cls, List<T> list,
                                                String unqKey) {

        return getResultListDataKey(jsondata, cls, "results", list, unqKey);
    }


    public static <T> List<T> getResultListData(Object jsondata, Class<T> cls, String key,
                                                List<T> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsondata.toString());
            JSONArray jsonArray = jsonObject.optJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItemObject = jsonArray.getJSONObject(i);
                try {
                    T tInfo = getGson().fromJson(jsonItemObject.toString(), cls);
                    list.add(tInfo);
                } catch (Exception e) {
                    e.printStackTrace();
//					Toast.makeText(BaseApp.getINSTANCE(), e+"", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<T> getListData(String jsonstr, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonstr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItemObject = jsonArray.getJSONObject(i);
                try {
                    T tInfo = getGson().fromJson(jsonItemObject.toString(), cls);
                    list.add(tInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //解析新动态发布的圈子 hj
    public static <T> List<T> getResultArrayListData(JSONArray jsonArray, Class<T> cls, String
            key, List<T> list) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItemObject = jsonArray.getJSONObject(i);
                JSONObject obj = jsonItemObject.getJSONObject(key);
                try {
                    T tInfo = getGson().fromJson(obj.toString(), cls);
                    list.add(tInfo);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static <T> List<T> getResultArrayListData(String jsonData, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonData);
            if (array != null && array.length() > 0) {
                JSONArray array1 = array.getJSONArray(0);
                if (array1 != null && array1.length() > 0) {
                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject jsonItemObject = array1.getJSONObject(i);
                        try {
                            T tInfo = getGson().fromJson(jsonItemObject.toString(), cls);
                            list.add(tInfo);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static <T> List<T> getResultArrayListData(String jsonData, Class<T> cls, List<T>
            list) throws JSONException {
        JSONArray array = new JSONArray(jsonData);
        return getResultArrayListData(array, cls, list);
    }

    public static <T> List<T> getResultArrayListData(JSONArray jsonArray, Class<T> cls, List<T>
            list) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItemObject = jsonArray.getJSONObject(i);
                try {
                    T tInfo = getGson().fromJson(jsonItemObject.toString(), cls);
                    list.add(tInfo);
                } catch (Exception e) {
//					Toast.makeText(BaseApp.getINSTANCE(), e+"", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static <T> List<T> getResultListDataKey(Object jsondata, Class<T> cls, String key,
                                                   List<T> list) {
        try {
            JSONObject jsonObject = new JSONObject(jsondata.toString());
            JSONArray jsonArray = jsonObject.optJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItemObject = jsonArray.getJSONObject(i);
                try {
                    T tInfo = getGson().fromJson(jsonItemObject.toString(), cls);
                    list.add(tInfo);
                } catch (Exception e) {
//					Toast.makeText(BaseApp.getINSTANCE(), e+"", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
        return list;
    }


    public static <T> List<T> getResultListDataKey(Object jsondata, Class<T> cls, String key,
                                                   List<T> list, String unqKey) {
        try {
            JSONObject jsonObject = new JSONObject(jsondata.toString());
//			JSONObject jsonArrayResults = jsonObject.getJSONObject("results");
            JSONArray jsonArray = jsonObject.optJSONArray(key);
            UnqKeyHt unqKeyHt = new UnqKeyHt();
            unqKeyHt.putAllID(list, unqKey);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItemObject = jsonArray.getJSONObject(i);
                try {
                    Object unqValue = jsonItemObject.optString(unqKey);
                    if (unqValue == null || unqValue.equals("")) {
                        unqValue = jsonItemObject.optInt(unqKey);
                    }
                    T tInfo = getGson().fromJson(jsonItemObject.toString(), cls);
                    int pos = unqKeyHt.getDataTable(unqValue);
                    if (pos != -1) {
                        list.set(pos, tInfo);
                    } else {
                        list.add(tInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(BaseApp.getINSTANCE(), e+"", 0).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //将对象、List转换成String
    public static <T> String getJson(T t) {
        String object = null;
        try {
            object = getGson().toJson(t);
        } catch (Exception e) {
        }
        return object;
    }

    public static <T> T getJson(String shareinfo, Class<T> cls) {
        T dgq = null;
        try {
            dgq = getGson().fromJson(shareinfo, cls);
        } catch (Exception e) {
        }
        return dgq;
    }

    public static <T> List<T> getResultListData(String jsondata, Class<T> cls) {

        return getResultListData(jsondata, cls, "results", new ArrayList<T>());
    }

    public static <T> List<T> gsonToList(String gsonStr, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonStr, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }


    static class UnqKeyHt {
        private Hashtable<Object, Integer> IDtabel;

        private UnqKeyHt() {
            IDtabel = new Hashtable<Object, Integer>();
        }

        public void putAllID(List list, String unqKey) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    Object model = list.get(i);
                    String jsonObject = getGson().toJson(model);
                    String value = getString(jsonObject, unqKey);
                    IDtabel.put(value, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int getDataTable(Object key) {
            if (IDtabel.get(key) == null) {
                return -1;
            } else {
                return IDtabel.get(key);
            }
        }
    }

    /**
     * 遍历泛型实体类
     *
     * @param model
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static String getClassValueByFiled(Object model) throws NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        for (int j = 0; j < field.length; j++) {     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字
            System.out.println("attribute name:" + name);
            name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString();    //获取属性的类型
            field[j].setAccessible(true);
            Method m = model.getClass().getMethod("get" + name);
            String value = m.invoke(model).toString();    //调用getter方法获取属性值
            if (value != null) {
                return value;
            }
        }
        return "";
    }

    /**
     * 解析Json数据
     *
     * @param key    更换数据key
     * @param value  更换Value
     * @param object 解析对象
     */
    public static void analyzeJson(String key, Object value, Object object) {
        try {
            if (object instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) object;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    analyzeJson(key, value, jsonObject);
                }
            } else if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                Iterator iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String jsonKey = iterator.next().toString();
                    Object ob = jsonObject.get(jsonKey);
                    if (ob != null) {
                        if (ob instanceof JSONArray) {
                            analyzeJson(key, value, ob);
                        } else if (ob instanceof JSONObject) {
                            analyzeJson(key, value, ob);
                        } else {
                            if (jsonKey.equals(key)) {
                                jsonObject.put(key, value);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

