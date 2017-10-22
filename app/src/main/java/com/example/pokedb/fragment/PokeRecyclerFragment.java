package com.example.pokedb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedb.Adapter.PokeAdapter;
import com.example.pokedb.Object.Pokemon;
import com.example.pokedb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokeRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Pokemon> pokeList;

    private List<Pokemon> initList(){
        List<Pokemon> pokeList = new ArrayList<>();
        pokeList.add(new Pokemon("#001", "妙蛙种子", R.drawable.dp_m_001));
        pokeList.add(new Pokemon("#002", "妙蛙草", R.drawable.dp_m_002));
        pokeList.add(new Pokemon("#003", "妙蛙花", R.drawable.dp_m_003));
        pokeList.add(new Pokemon("#004", "小火龙", R.drawable.dp_m_004));
        pokeList.add(new Pokemon("#005", "火恐龙", R.drawable.dp_m_005));
        pokeList.add(new Pokemon("#006", "喷火龙", R.drawable.dp_m_006));
        pokeList.add(new Pokemon("#007", "杰尼龟", R.drawable.dp_m_007));
        pokeList.add(new Pokemon("#008", "卡咪龟", R.drawable.dp_m_008));
        pokeList.add(new Pokemon("#009", "水箭龟", R.drawable.dp_m_009));
        return pokeList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poke_recycler, container, false);

        //初始化RecyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.poke_recycler_view);
        if (pokeList==null){
            pokeList = initList();
        }
        PokeAdapter pokeAdapter = new PokeAdapter(getActivity(), pokeList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokeAdapter);

        return view;
    }


}
