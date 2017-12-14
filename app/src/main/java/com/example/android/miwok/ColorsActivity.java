package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> colorWord = new ArrayList<Word>();

        colorWord.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorWord.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorWord.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorWord.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorWord.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colorWord.add(new Word("white", "ṭopiisә", R.drawable.color_white, R.raw.color_white));
        colorWord.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorWord.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw
                .color_mustard_yellow));

        WordAdapter colorAdapter = new WordAdapter(this, colorWord, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(colorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, R.raw.color_black);
            }
        });
    }
}
