package com.gate.big.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.util.Assert;

// gson
public class GsonUtil {

    // 创建方式
    public static Gson getGson1() {
        Gson gson = new Gson();
        return gson;
    }
    public static Gson getGson2() {
        Gson gson = new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation() //不对没有用@Expose注解的属性进行操作
                //.enableComplexMapKeySerialization() //当Map的key为复杂对象时,需要开启该方法
                //.serializeNulls() //当字段值为空或null时，依然对该字段进行转换
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //时间转化为特定格式
                .setPrettyPrinting() //对结果进行格式化，增加换行
                .disableHtmlEscaping() //防止特殊字符出现乱码
                //.registerTypeAdapter(User.class,new UserAdapter()) //为某特定对象设置固定的序列或反序列方式，自定义Adapter需实现JsonSerializer或者JsonDeserializer接口
                .create();
        return gson;
    }

    public static String getJson(Object object) {
        String jsonStr = getGson2().toJson(object);
        return jsonStr;
    }

    public static Object fromJson(String json, Class clazz ) {
        Object target = getGson2().fromJson(json, clazz);
        return target;
    }

    public static void main(String[] args) {
        A a = new A();
        a.setA("a");
        a.setB("b");
        String json = getJson(a);
        System.out.println(json);
        A a2 = (A) fromJson(json,A.class);
    }

    static class A {
        String a;
        String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
