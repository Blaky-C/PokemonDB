package com.example.pokedb.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.pokedb.Http.Parser;
import com.example.pokedb.Object.Pokemon;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017/10/26.
 */

public class PokeDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_POKE_TABLE = "create table basic_info(" +
            "id text primary key," +
            "c_name text," +
            "j_name text," +
            "e_name text," +
            "type_1 text," +
            "type_2 text)";

    public Context context;
    public List<Pokemon> pokeList = new ArrayList<>();

    private void initList(){
        //手动初始化数据库
        /*int i=1;
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
        pokeList.add(new Pokemon(i++, "雷丘", "ライチュウ", "Raichu"));*/

        //从网络页面获取信息
        String address = "http://www.pokemon.name/wiki/%E5%85%B3%E9%83%BD%E5%9B%BE%E9%89%B4";
        Parser.parseHTML(address, new Parser.HTMLParserListener() {
            @Override
            public void onFinish(Document response) {
                Elements elements = response.select("tr");
                for (int i = 1; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    Elements attr = element.select("td");
                    String id = attr.get(0).html();
                    id = "#"+id+"00";
                    String cname = attr.get(2).select("a").html();
                    String jname = attr.get(3).html();
                    String ename = attr.get(4).html();
                    String type1 = null;
                    String type2 = null;
                    Elements types = attr.get(5).select("span");
                    if (types.size() == 1) {
                        type1 = types.get(0).html();
                        type2 = "/";
                    } else {
                        type1 = types.get(0).html();
                        type2 = types.get(1).html();
                    }
                    PokeDBHelper.this.pokeList.add(new Pokemon(id, cname, jname, ename, type1, type2));
                    Log.d("PokeDbHelper", pokeList.size()+"");
                }
                Log.d("PokeDBHelper", "List initialized successfully.");
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                Log.d("PokeDBHelper", "List initialized failed.");
            }
        });
    }

    public PokeDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //建立数据表
        sqLiteDatabase.execSQL(CREATE_POKE_TABLE);

        //初始化数据
        initList();

        //需要让主线程停止10秒等待数据库更新完毕
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }

        //将pokeList数据导入数据库
        String sql_insert = "insert into basic_info values(?, ?, ?, ?, ?, ?)";
        Pokemon p = null;
        Log.d("PokeDbHelper", pokeList.size()+"");
        for(int i=0;i<pokeList.size();i++){
            p = pokeList.get(i);
            String id = p.getId();
            String c_name = p.getC_name();
            String j_name = p.getJ_name();
            String e_name = p.getE_name();
            String type_1 = p.getType_1();
            String type_2 = p.getType_2();
            Log.d("PokeDBHelper", id+" "+c_name+" "+" "+j_name+" "+e_name+" "+type_1+" "+type_2);

            sqLiteDatabase.execSQL(sql_insert, new String[]{id, c_name, j_name, e_name, type_1, type_2});
        }

        Toast.makeText(context, "PokeDB Table Created.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
