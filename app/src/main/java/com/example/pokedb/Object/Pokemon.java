package com.example.pokedb.Object;

/**
 * Created by Jack on 2017/10/20.
 */

public class Pokemon {
    String id;
    String c_name;
    String j_name;
    String e_name;
    String type_1;
    String type_2;

    public Pokemon(int id, String c_name, String j_name, String e_name, String t1, String t2) {
        this.id = "#"+String.format("%03d", id)+"00";
        this.c_name = c_name;
        this.j_name = j_name;
        this.e_name = e_name;
        this.type_1 = t1;
        this.type_2 = t2;
    }

    public Pokemon(String id, String c_name, String j_name, String e_name, String t1, String t2) {
        this.id = id;
        this.c_name = c_name;
        this.j_name = j_name;
        this.e_name = e_name;
        this.type_1 = t1;
        this.type_2 = t2;
    }

    public String getC_name() {
        return c_name;
    }

    public String getJ_name() {
        return j_name;
    }

    public String getE_name() {
        return e_name;
    }

    public String getId(){
        return id;
    }

    public static String getId(int id){
        //这里的id仅返回001格式
        return String.format("%03d", id);
    }

    public String getType_1() {
        return type_1;
    }

    public String getType_2() {
        return type_2;
    }

    /*public static String getRawImageName(int id){
        return "raw_"+String.format("%03d", id);
    }

    public static String getHgssImageName(int id){
        return "hgss_"+String.format("%03d", id);
    }*/
}
