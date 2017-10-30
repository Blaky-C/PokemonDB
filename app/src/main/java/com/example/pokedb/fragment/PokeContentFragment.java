package com.example.pokedb.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedb.Http.HttpUtil;
import com.example.pokedb.Object.Pokemon;
import com.example.pokedb.R;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class PokeContentFragment extends Fragment {

    private TextView pokeId;
    private TextView pokeName;
    private ImageView pokeImage;
    private View view;

    private String id;
    private String name;
    private String image;
    private AppCompatActivity activity;

    public PokeContentFragment() {
        super();
    }

    //通过构造函数初始化Fragment对象里的资源值
    public PokeContentFragment(String id, String name, String image, AppCompatActivity a) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.activity = a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_poke_content, container, false);
        this.pokeId = (TextView)view.findViewById(R.id.poke_id);
        this.pokeName = (TextView)view.findViewById(R.id.poke_name);
        this.pokeImage = (ImageView)view.findViewById(R.id.poke_image);

        //若id不为null，必定为单页模式
        if (id!=null){
            this.refresh(id, name, image);
        }
        return view;
    }

    public void refresh(String pokeId, String pokeName, final String pokeAdress) {
        //view.setVisibility(View.VISIBLE);

        this.pokeId.setText(pokeId);
        this.pokeName.setText(pokeName);

        Log.d("FragmentContent", pokeAdress);

        //调用Thread线程失败
        HttpUtil.getServerPic(pokeAdress, new HttpUtil.PicCallbackListener() {
            @Override
            public void onFinish(final Bitmap bitmap) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pokeImage.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
        //this.pokeImage.setImageResource(pokeImage);

    }
}
