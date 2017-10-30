package com.example.pokedb.Adapter;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedb.Http.HttpUtil;
import com.example.pokedb.Object.Pokemon;
import com.example.pokedb.PokeContentActivity;
import com.example.pokedb.R;
import com.example.pokedb.fragment.PokeContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017/10/21.
 */

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.ViewHolder> {

    private List<Pokemon> pokeList = new ArrayList<>();
    private AppCompatActivity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView pokeName;
        TextView pokeId;
        ImageView pokeImage;

        View view;

        public ViewHolder(View view){
            super(view);
            pokeId = (TextView)view.findViewById(R.id.poke_id);
            pokeName = (TextView)view.findViewById(R.id.poke_name);
            pokeImage = (ImageView)view.findViewById(R.id.poke_image);
            this.view = view;
        }
    }

    public PokeAdapter(Activity activity, Cursor c){
        this.activity = (AppCompatActivity)activity;
        int i=1;
        while (c.moveToNext()){
            pokeList.add(new Pokemon(i++, c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
        }
        c.close();

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poke_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取Pokemon对应的
                int id = holder.getAdapterPosition()+1;
                Pokemon pokemon = pokeList.get(holder.getAdapterPosition());
                boolean isTwo = view.getRootView().findViewById(R.id.poke_content_layout)!=null;
                if (!isTwo){
                    PokeContentActivity.actionStart(parent.getContext(), pokemon);
                }else{
                    //直接进行片段的刷新
                    /*PokeContentFragment pokeContentFragment = (PokeContentFragment) activity.getSupportFragmentManager().findFragmentById(R.id.poke_content);

                    String newContentImage = "poke_"+pokemon.getId().substring(1);
                    int newResource = view.getResources().getIdentifier(newContentImage, "drawable", view.getContext().getPackageName());
                    pokeContentFragment.refresh(pokemon.getId(), pokemon.getName(), newResource);*/

                    //使用FrameLayout对片段进行替换
                    //获取图片资源
                    //int imageResource = view.getResources().getIdentifier(Pokemon.getRawImageName(id), "drawable", view.getContext().getPackageName());
                    //pokeContentFragment.refresh(pokemon.getId(), pokemon.getName(), newResource);
                    String imageResource = "";

                    PokeContentFragment pokeContentFragment = new PokeContentFragment("#"+Pokemon.getId(id), pokemon.getC_name(), imageResource, new AppCompatActivity());
                    FragmentManager fragmentManager =activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.poke_content_layout, pokeContentFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Pokemon pokemon = pokeList.get(position);
        holder.pokeId.setText(pokemon.getId().substring(0, 4));
        holder.pokeName.setText(pokemon.getC_name());

        View view = holder.view;
        String id = Pokemon.getId(position+1)+".00.png";
        String dirAddress = "http://www.craftmanjack.cn/PokeDB/Pictures/hgss/";

        HttpUtil.getServerPic(dirAddress + id, new HttpUtil.PicCallbackListener() {
            @Override
            public void onFinish(Bitmap b) {
                holder.pokeImage.setImageBitmap(b);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }
}

