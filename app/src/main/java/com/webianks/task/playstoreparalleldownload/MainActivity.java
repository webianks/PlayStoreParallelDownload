package com.webianks.task.playstoreparalleldownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        String jsonObject = loadJSONFromAsset();
        parseJson(jsonObject);

    }


    private void parseJson(String jsonObject) {

        List<App> appsList = new ArrayList<>();

        try {

            JSONObject appJsonObject = new JSONObject(jsonObject);
            JSONArray appArray = appJsonObject.getJSONArray("apps");

            for (int i = 0; i < appArray.length(); i++) {

                JSONObject appObject = appArray.getJSONObject(i);
                String appName = appObject.getString("name");
                String developer = appObject.getString("developer");
                String download_link = appObject.getString("download-link");
                String app_icon = appObject.getString("app_icon");
                String stars = appObject.getString("stars");

                App app = new App();
                app.setAppName(appName);
                app.setDeveloper(developer);
                app.setDownloadLink(download_link);
                app.setAppIcon(app_icon);
                app.setStars(stars);

                appsList.add(app);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        setAdapter(appsList);

    }

    private void setAdapter(List<App> appsList) {
        PlayRecyclerAdapter playRecyclerAdapter = new PlayRecyclerAdapter(this, appsList);
        recyclerView.setAdapter(playRecyclerAdapter);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("apps.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
