package com.project.howtoplaysearchapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.project.howtoplaysearchapp.R;
import com.project.howtoplaysearchapp.models.YoutubeDataModel;
import com.squareup.picasso.Picasso;

public class YoutubeDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String YOUTUBE_API_KEY = "AIzaSyAZmYPmEBf-8JqyAQGzFMhvzVOmrzhuF9o";
    public static final String VIDEO_ID = "";
    private YouTubePlayerView youTubePlayerView = null;
    private YoutubeDataModel youtubeDataModel = null;
    private RecyclerView list_videos = null;

    TextView detail_title;
    TextView detail_desc;
    TextView detail_date;
    ImageView detail_thumbnail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_details);

        youtubeDataModel = getIntent().getParcelableExtra(YoutubeDataModel.class.toString());
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(YOUTUBE_API_KEY, this);


        detail_title = (TextView) findViewById(R.id.detail_title);
        detail_desc = (TextView) findViewById(R.id.detail_desc);
        detail_date = (TextView) findViewById(R.id.detail_date);
//        detail_thumbnail = (ImageView) findViewById(R.id.detail_thumbnail);

        detail_title.setText(youtubeDataModel.getTitle());
        detail_date.setText(youtubeDataModel.getDate());
        detail_desc.setText(youtubeDataModel.getDesciption());

        list_videos = (RecyclerView) findViewById(R.id.list_videos);

        try{
            if(youtubeDataModel.getThumbnail() != null){
                if(youtubeDataModel.getThumbnail().startsWith("http")){
                    Picasso.with(this).load(youtubeDataModel.getThumbnail()).into(detail_thumbnail);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }



    }

    public void back_btn_pressed(View view){
        finish();
    }
    /*
    public void playVideo(View view){
        Intent intent = new Intent(this, VideoPlayActivity.class);
        startActivity(intent);
    }
*/
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {


        /*if(VIDEO_ID != null){
            youTubePlayer.loadVideo(VIDEO_ID);
            youTubePlayer.play();
        }else{

            Log.e("VIDEO ACTIVITY", "VIDEO ID IS NULL "+VIDEO_ID);
        }*/

        if (!wasRestored) {
            Log.e("VIDEO ACTIVITY", "VIDEO ID IS NULL "+youtubeDataModel.getVideo_id());
            youTubePlayer.cueVideo(youtubeDataModel.getVideo_id());
        }
        youTubePlayer = youTubePlayer;
    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
