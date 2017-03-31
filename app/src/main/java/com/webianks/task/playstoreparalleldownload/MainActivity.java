package com.webianks.task.playstoreparalleldownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<App> appsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        App app = new App();
        app.setAppLogo(R.drawable.ic_instagram);
        app.setAppName(getString(R.string.instagram));
        app.setDeveloper(getString(R.string.instagram));
        app.setStars(4.5f);

        appsList.add(app);

        PlayRecyclerAdapter playRecyclerAdapter = new PlayRecyclerAdapter(this,appsList);
        recyclerView.setAdapter(playRecyclerAdapter);
    }
}
