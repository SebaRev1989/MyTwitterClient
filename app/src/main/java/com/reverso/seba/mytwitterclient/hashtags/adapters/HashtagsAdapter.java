package com.reverso.seba.mytwitterclient.hashtags.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reverso.seba.mytwitterclient.R;
import com.reverso.seba.mytwitterclient.hashtags.entities.Hashtag;
import com.reverso.seba.mytwitterclient.hashtags.ui.CustomGridLayoutManager;
import com.reverso.seba.mytwitterclient.hashtags.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by seba on 29/06/16.
 */
public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder>{
    private Context context;
    private List<Hashtag> items;
    private OnItemClickListener clickListener;

    public HashtagsAdapter(Context context, List<Hashtag> items, OnItemClickListener clickListener) {
        this.context = context;
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hashtag, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag tweet = items.get(position);
        holder.setClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Hashtag> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtTweet)
        TextView txtTweet;
        @Bind(R.id.recyclerViewHashtags)
        RecyclerView recyclerView;

        private View view;
        private ArrayList<String> items;
        private HashtagsListAdapter adapter;

        public ViewHolder(View view, Context context) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

            items = new ArrayList<String>();
            adapter = new HashtagsListAdapter(items);
            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        public void setClickListener(final Hashtag tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });
        }

        public void setItems(List<String> newItems) {
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }
    }
}
