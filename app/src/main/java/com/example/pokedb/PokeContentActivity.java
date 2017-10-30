package com.example.pokedb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pokedb.Object.Pokemon;
import com.example.pokedb.fragment.PokeContentFragment;

public class PokeContentActivity extends AppCompatActivity {

    private PokeContentFragment pokeContentFragment;

    public static void actionStart(Context context, Pokemon pokemon){
        Intent intent = new Intent(context, PokeContentActivity.class);

        String idView = pokemon.getId();
        int id = Integer.parseInt(idView.substring(1,4));

        String dirAddress = "http://www.craftmanjack.cn/PokeDB/Pictures/raw/";
        String address = dirAddress+Pokemon.getId(id)+".00.png";

        intent.putExtra("poke_id", "#"+Pokemon.getId(id));
        intent.putExtra("poke_name", pokemon.getC_name()+"\n"+pokemon.getE_name()+"\n"+pokemon.getJ_name()+"\n"+pokemon.getType_1()+"\t"+(("/".equals(pokemon.getType_2()))?"":pokemon.getType_2()));
        intent.putExtra("poke_image", address);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_content);

        Intent intent = getIntent();
        String pokeId = intent.getStringExtra("poke_id");
        String pokeName = intent.getStringExtra("poke_name");
        String pokeImage = intent.getExtras().getString("poke_image");
        Log.d("MainActivity", pokeImage);

        /*pokeContentFragment = (PokeContentFragment)getSupportFragmentManager().findFragmentById(R.id.poke_content);
        pokeContentFragment.refresh(pokeId, pokeName, pokeImage);*/
        PokeContentFragment pokeContentFragment = new PokeContentFragment(pokeId, pokeName, pokeImage, this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.poke_content, pokeContentFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
