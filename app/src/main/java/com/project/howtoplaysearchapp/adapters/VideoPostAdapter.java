package com.project.howtoplaysearchapp.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.howtoplaysearchapp.R;
import com.project.howtoplaysearchapp.models.YoutubeDataModel;
import com.squareup.picasso.Picasso;

import com.project.howtoplaysearchapp.interfaces.OnItemClickListener;
import java.util.ArrayList;


public class VideoPostAdapter extends RecyclerView.Adapter<VideoPostAdapter.YoutubePostHolder> {


    private ArrayList<YoutubeDataModel> dataSet;
    private Context context = null;
    private final OnItemClickListener listener;


    public VideoPostAdapter(Context context, ArrayList<YoutubeDataModel> dataSet, OnItemClickListener listner){
        this.dataSet = dataSet;
        this.context = context;
        this.listener = listner;
    }

    @Override
    public YoutubePostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_post_layout, parent, false);
        YoutubePostHolder postHolder = new YoutubePostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubePostHolder holder, int position) {


        TextView  tv_title = holder.tv_title;
        TextView tv_desc = holder.tv_desc;
        TextView tv_date = holder.tv_date;
        ImageView thumbnail = holder.thumbnail;

        YoutubeDataModel object = dataSet.get(position);

        tv_title.setText(object.getTitle());
        tv_desc.setText(object.getDesciption());
        tv_date.setText(object.getDate());

        holder.bind(dataSet.get(position), listener);

        Picasso.with(context).load(object.getThumbnail()).into(thumbnail);

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class YoutubePostHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_desc;
        TextView tv_date;
        ImageView thumbnail;

        public YoutubePostHolder(View itemView) {

            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            this.tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);


        }


        public void bind(final YoutubeDataModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}

