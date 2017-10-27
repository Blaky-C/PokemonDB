package com.example.pokedb.Object;

/**
 * Created by Jack on 2017/10/20.
 */

public class Pokemon {
    String id;
    String c_name;
    String j_name;
    String e_name;

    public Pokemon(int id, String c_name, String j_name, String e_name) {
        this.id = String.format("%03d", id);
        this.c_name = c_name;
        this.j_name = j_name;
        this.e_name = e_name;
    }

    public Pokemon(String id, String c_name, String j_name, String e_name) {
        this.id = id;
        this.c_name = c_name;
        this.j_name = j_name;
        this.e_name = e_name;
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
        return String.format("%03d", id);
    }

    public static String getRawImageName(int id){
        return "raw_"+String.format("%03d", id);
    }

    public static String getHgssImageName(int id){
        return "hgss_"+String.format("%03d", id);
    }
}
