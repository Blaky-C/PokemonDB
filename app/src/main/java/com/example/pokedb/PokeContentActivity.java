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

        int id = Integer.parseInt(pokemon.getId());
        //这里替换原来的图片id
        int newResource = context.getResources().getIdentifier(Pokemon.getRawImageName(id), "drawable", context.getPackageName());

        intent.putExtra("poke_id", "#"+Pokemon.getId(id));
        intent.putExtra("poke_name", pokemon.getC_name());
        intent.putExtra("poke_image", newResource);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_content);

        Intent intent = getIntent();
        String pokeId = intent.getStringExtra("poke_id");
        String pokeName = intent.getStringExtra("poke_name");
        int pokeImage = intent.getExtras().getInt("poke_image");
        Log.d("MainActivity", pokeImage+"");

        /*pokeContentFragment = (PokeContentFragment)getSupportFragmentManager().findFragmentById(R.id.poke_content);
        pokeContentFragment.refresh(pokeId, pokeName, pokeImage);*/
        PokeContentFragment pokeContentFragment = new PokeContentFragment(pokeId, pokeName, pokeImage);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.poke_content, pokeContentFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
