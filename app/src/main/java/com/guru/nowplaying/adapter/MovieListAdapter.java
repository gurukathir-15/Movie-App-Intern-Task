package com.guru.nowplaying.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guru.nowplaying.R;
import com.guru.nowplaying.constants.Constants;
import com.guru.nowplaying.datamodel.NowPlayingList;
import com.guru.nowplaying.helpers.db.NowPlayListDBHelper;
import com.guru.nowplaying.interfaces.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Guru on 05-04-2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {


    ArrayList<NowPlayingList> mNowPlayArrayList;
    OnItemClickListener mSetClickListener;
    int mListItemView;
    public static String TAG = "MovieListAdapter";



    public MovieListAdapter(int pListItem,ArrayList<NowPlayingList> pNowPlayingList)
    {
        mNowPlayArrayList = pNowPlayingList;
        mListItemView = pListItem;
        Log.d(TAG+" constructor","Recieved Size"+mNowPlayArrayList.size());
//        Log.d(TAG,mNowPlayArrayList.get(0).getTitle());

    }

    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context lContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(lContext);
        View NowplayingListView = layoutInflater.inflate(mListItemView, parent, false);

        ViewHolder viewHolder = new ViewHolder(NowplayingListView);
        Log.d(TAG,"Oncreate viewholder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ViewHolder holder, int position) {

        NowPlayingList lNowPlayingList = mNowPlayArrayList.get(position);
        TextView lTitle = holder.lTitle;
        ImageView lPoster = holder.lPoster;
        lTitle.setText(" "+lNowPlayingList.getTitle());
        Picasso.get().load(Constants.IMAGE_PREFIX+lNowPlayingList.getPoster_path()).into(lPoster);
        Log.d(TAG,"OnBindview"+mNowPlayArrayList.size());

    }

    @Override
    public int getItemCount(){

        Log.d(TAG,"GetItemCount"+mNowPlayArrayList.size());
       return mNowPlayArrayList.size();
        //return 0;
    }

    public void setClickListener(OnItemClickListener onItemClickListener)
    {
        this.mSetClickListener = onItemClickListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener {

        TextView lTitle;
        ImageView lPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            lTitle = itemView.findViewById(R.id.movie_title);
            lPoster = itemView.findViewById(R.id.poster);
           // itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(int pPosition) {
            onClick(getAdapterPosition());
        }
    }
}
