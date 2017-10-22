package com.example.pokedb.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedb.Object.Pokemon;
import com.example.pokedb.PokeContentActivity;
import com.example.pokedb.R;
import com.example.pokedb.fragment.PokeContentFragment;

import java.util.List;

/**
 * Created by Jack on 2017/10/21.
 */

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.ViewHolder> {

    private List<Pokemon> pokeList;
    private AppCompatActivity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView pokeName;
        TextView pokeId;
        ImageView pokeImage;

        public ViewHolder(View view){
            super(view);
            pokeId = (TextView)view.findViewById(R.id.poke_id);
            pokeName = (TextView)view.findViewById(R.id.poke_name);
            pokeImage = (ImageView)view.findViewById(R.id.poke_image);
        }
    }

    public PokeAdapter(Activity activity, List<Pokemon> pokeList){
        this.activity = (AppCompatActivity)activity;
        this.pokeList = pokeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poke_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    String newContentImage = "poke_"+pokemon.getId().substring(1);
                    int newResource = view.getResources().getIdentifier(newContentImage, "drawable", view.getContext().getPackageName());
                    //pokeContentFragment.refresh(pokemon.getId(), pokemon.getName(), newResource);

                    PokeContentFragment pokeContentFragment = new PokeContentFragment(pokemon.getId(), pokemon.getName(), newResource);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon pokemon = pokeList.get(position);
        holder.pokeId.setText(pokemon.getId());
        holder.pokeName.setText(pokemon.getName());
        holder.pokeImage.setImageResource(pokemon.getImageId());
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }
}

