package com.project.tryboardgames.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.tryboardgames.R;
import com.project.tryboardgames.adapters.VideoPostAdapter;
import com.project.tryboardgames.models.YoutubeDataModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends Fragment {

    private static String YOUTUBE_API_KEY = "AIzaSyAZmYPmEBf-8JqyAQGzFMhvzVOmrzhuF9o";
    private static String query_start = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&order=date&q=How%20to%20play%20";
    private static String query_end = "&key=";
    private static String query = "Jenga";

    private RecyclerView list_videos = null;
    private VideoPostAdapter adapter = null;
    private ArrayList<YoutubeDataModel> list_data = new ArrayList<>();

    public YoutubeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        list_videos = (RecyclerView) view.findViewById(R.id.list_videos);
        initList(list_data);
        new RequestYoutubeAPI().execute();
        return view;
    }
    public void initList(ArrayList<YoutubeDataModel> list_data){

        list_videos.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new VideoPostAdapter(getActivity(), list_data);
        list_videos.setAdapter(adapter);
    }

    public class RequestYoutubeAPI extends AsyncTask<Void, String, String>{

        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(query_start+query+query_end+YOUTUBE_API_KEY);
            Log.e("URL", query_start+query+query_end+YOUTUBE_API_KEY);
            try{

                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(response != null){

                try{

                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    list_data = parseVideoListFromResponse(jsonObject);
                    adapter.notifyDataSetChanged();
                    initList(list_data);

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }

        private ArrayList<YoutubeDataModel> parseVideoListFromResponse(JSONObject jsonObject){
            ArrayList<YoutubeDataModel> list = new ArrayList<>();

            if(jsonObject.has("items")){
                try{

                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject json = jsonArray.getJSONObject(i);

                        if(json.has("id")){

                            JSONObject jsonID = json.getJSONObject("id");

                            if(jsonID.has("kind")){

                                if(jsonID.getString("kind").equals("youtube#video")){

                                    JSONObject jsonSnippet = json.getJSONObject("snippet");
                                    String title = jsonSnippet.getString("title");
                                    String description = jsonSnippet.getString("description");
                                    String date = jsonSnippet.getString("publishedAt");
                                    String thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");

                                    YoutubeDataModel youtube = new YoutubeDataModel();
                                    youtube.setTitle(title);
                                    youtube.setDate(date);
                                    youtube.setDesciption(description);
                                    youtube.setThumbnail(thumbnail);
                                    list.add(youtube);
                                }
                            }
                        }


                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
            return list;
        }
    }

}
