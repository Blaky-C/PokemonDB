package com.example.pokedb.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedb.Adapter.PokeAdapter;
import com.example.pokedb.Object.Pokemon;
import com.example.pokedb.R;
import com.example.pokedb.SQLite.PokeDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokeRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private Cursor cursor;
    private PokeDBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poke_recycler, container, false);

        //开始时从数据库查询数据
        dbHelper = new PokeDBHelper(getContext(), "PokeDB.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql_query = "select * from poke_table";
        cursor = db.rawQuery(sql_query, new String[]{});

        //初始化RecyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.poke_recycler_view);

        //使用Cursor初始化Adapter
        PokeAdapter pokeAdapter = new PokeAdapter(getActivity(), cursor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokeAdapter);

        //关闭数据库
        cursor.close();
        db.close();
        return view;
    }


}
