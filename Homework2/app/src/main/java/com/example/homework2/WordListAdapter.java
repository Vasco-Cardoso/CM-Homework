package com.example.homework2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {

    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;
    private Context c;
    public SecondFragment secondfrag = SecondFragment.newInstance();
    public Bundle dataBundle = new Bundle();

    public WordListAdapter(Context context,
                           LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        c = context;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int pos) {

                String gTitle = mWordList.get(pos);
                dataBundle.putString("title", gTitle);
                secondfrag.setArguments(dataBundle);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, secondfrag).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        ItemClickListener itemClickListener;

        WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());

        }

        public void setItemClickListener(ItemClickListener ic){

            this.itemClickListener = ic;
        }
    }
}