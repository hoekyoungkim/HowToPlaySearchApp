package com.project.tryboardgames.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.tryboardgames.R;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class WikipediaFragment extends Fragment {


    private String query_start = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
    private String query_end  = "";
    private String query = "Jenga";


    private String result = "";
    public WikipediaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wikipedia, container, false);
        new RequestWikipediaAPI().execute();
        return view;
    }

    public class RequestWikipediaAPI extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... voids) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(query_start + query + query_end);
            Log.e("Wikipedia URL", query_start + query + query_end);

            try {

                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String response) {

            super.onPostExecute(response);
            if (response != null) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    result = parseWikiContentFromResponse(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        private String parseWikiContentFromResponse(JSONObject jsonObject) {

            Log.e("parse Wiki Content", "***************************");


            try{
                if(jsonObject.has("query")) {

                    JSONObject query = jsonObject.getJSONObject("query");

                    if(query.has("pages")){
                        JSONObject pages = query.getJSONObject("pages");
                        Iterator<String> keys = pages.keys(); //jsonObject.getJSONObject("query").getJSONObject("pages").keys()

                        JSONObject contents = pages.getJSONObject(keys.next());

                        Log.e("parsed Wiki Content", contents.toString());

                        if(contents.has("extract")){
                            Log.e("parsed Wiki Content !!!", contents.getString("extract"));
                            //R.id.wiki_extract = contents.getString("extract");
                            TextView wiki_extract = (TextView) getView().findViewById(R.id.wiki_extract);
                            wiki_extract.setText(contents.getString("extract"));
                        }
                        if(contents.has("title")){
                            Log.e("parsed Wiki Content !!!", contents.getString("title"));
                            TextView wiki_title = (TextView) getView().findViewById(R.id.wiki_title);
                            wiki_title.setText(contents.getString("title"));

                            //R.id.wiki_extract = contents.getString("extract");

                        }



                    }
                }
            }catch(JSONException e){
                e.printStackTrace();
            }

            return "";
        }
    }
}
