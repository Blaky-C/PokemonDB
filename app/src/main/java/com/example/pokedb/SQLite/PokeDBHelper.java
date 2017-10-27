package com.example.pokedb.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.pokedb.Object.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017/10/26.
 */

public class PokeDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_POKE_TABLE = "create table poke_table(" +
            "id text primary key," +
            "c_name text," +
            "j_name text," +
            "e_name text)";

    public Context context;
    public List<Pokemon> pokeList = new ArrayList<>();

    private void initList(){
        //手动初始化数据库
        int i=1;
        pokeList.add(new Pokemon(i++, "妙蛙种子", "フシギダネ", "Bulbasaur"));
        pokeList.add(new Pokemon(i++, "妙蛙草", "フシギソウ", "Ivysaur"));
        pokeList.add(new Pokemon(i++, "妙蛙花", "フシギバナ", "Venusaur"));
        pokeList.add(new Pokemon(i++, "小火龙", "ヒトカゲ", "Charmander"));
        pokeList.add(new Pokemon(i++, "火恐龙", "リザード", "Charmeleon"));
        pokeList.add(new Pokemon(i++, "喷火龙", "リザードン", "Charizard"));
        pokeList.add(new Pokemon(i++, "杰尼龟", "ゼニガメ", "Squirtle"));
        pokeList.add(new Pokemon(i++, "卡咪龟", "カメール", "Wartortle"));
        pokeList.add(new Pokemon(i++, "水箭龟", "カメックス", "Blastoise"));
        pokeList.add(new Pokemon(i++, "绿毛虫", "キャタピー", "Caterpie"));
        pokeList.add(new Pokemon(i++, "铁甲蛹", "トランセル", "Metapod"));
        pokeList.add(new Pokemon(i++, "巴大蝶", "バタフリー", "Butterfree"));
        pokeList.add(new Pokemon(i++, "独角虫", "ビードル", "Weedle"));
        pokeList.add(new Pokemon(i++, "铁壳蛹", "コクーン", "Kakuna"));
        pokeList.add(new Pokemon(i++, "大针蜂", "スピアー", "Beedrill"));
        pokeList.add(new Pokemon(i++, "波波", "ポッポ", "Pidgey"));
        pokeList.add(new Pokemon(i++, "比比鸟", "ピジョン", "Pidgeotto"));
        pokeList.add(new Pokemon(i++, "大比鸟", "ピジョット", "Pidgeot"));
        pokeList.add(new Pokemon(i++, "小拉达", "コラッタ", "Rattata"));
        pokeList.add(new Pokemon(i++, "拉达", "ラッタ", "Raticate"));
        pokeList.add(new Pokemon(i++, "烈雀", "オニスズメ", "Spearow"));
        pokeList.add(new Pokemon(i++, "大嘴雀", "オニドリル", "Fearow"));
        pokeList.add(new Pokemon(i++, "阿柏蛇", "アーボ", "Ekans"));
        pokeList.add(new Pokemon(i++, "阿柏怪", "アーボック", "Arbok"));
        pokeList.add(new Pokemon(i++, "皮卡丘", "ピカチュウ", "Pikachu"));
        pokeList.add(new Pokemon(i++, "雷丘", "ライチュウ", "Raichu"));
    }

    public PokeDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_POKE_TABLE);
        Toast.makeText(context, "PokeDB Table Created.", Toast.LENGTH_SHORT).show();

        initList();

        //将pokeList数据导入数据库
        String sql_insert = "insert into poke_table values(?, ?, ?, ?)";
        Pokemon p = null;

        for(int i=0;i<pokeList.size();i++){
            p = pokeList.get(i);
            String id = p.getId();
            String c_name = p.getC_name();
            String j_name = p.getJ_name();
            String e_name = p.getE_name();
            sqLiteDatabase.execSQL(sql_insert, new String[]{id, c_name, j_name, e_name});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
