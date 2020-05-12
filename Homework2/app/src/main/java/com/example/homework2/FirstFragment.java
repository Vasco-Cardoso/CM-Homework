package com.example.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework2.datamodel.City;
import com.example.homework2.datamodel.WeatherType;
import com.example.homework2.network.IpmaWeatherClient;

import java.util.HashMap;
import java.util.LinkedList;

public class FirstFragment extends Fragment {

    private TextView feedback;

    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;


    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;


    public static FirstFragment newInstance() {

        return new FirstFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View v = inflater.inflate(R.layout.fragment_first, container, false);

        mWordList.clear();
        mWordList.addLast("Aveiro");
        mWordList.addLast("Lisboa");
        mWordList.addLast("Porto");
        mWordList.addLast("Portalegre");
        mWordList.addLast("Sintra");

        // Get a handle to the RecyclerView.
        mRecyclerView = v.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(v.getContext(), mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}
