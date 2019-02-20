package com.project.tryboardgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.api.services.youtube.YouTube;
import com.google.android.youtube.player.YouTubePlayerFragment;


public class YoutubeListActiviy extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    YouTubePlayerFragment  myouTubePlayerFragment ;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    private static YouTube youtube;
    private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    public static final String YOUTUBE_API_KEY = "AIzaSyBnEHLsXZ3jI1DHveqE4cotp5KqzcmtxAw";

    //GoogleAccountCredential mCredential;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list_activiy);

        Log.d("YoutubeListActiviy","onCreate: Starting.");
        btnPlay = (Button) findViewById(R.id.btnPlay);
        // java.lang.NullPointerException: Attempt to invoke virtual method 'void com.google.android.youtube.player.YouTubePlayerFragment.initialize(java.lang.String, com.google.android.youtube.player.YouTubePlayer$OnInitializedListener)' on a null object reference
        myouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        myouTubePlayerFragment.initialize("api key",this);


        Log.d("YoutubeListActiviy","get Youtube playlist");

        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("YoutubeListActiviy","onClick: Initializing YouTube player.");
                myouTubePlayerFragment.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);
            }

        });

    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            Log.d("YoutubeListActiviy","onClick: Done initializing.");
            youTubePlayer.cueVideo("W4hTJybfU7s");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
