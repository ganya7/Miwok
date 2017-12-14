package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.

                        releaseMediaPlayer();
                    } else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, because something else is also
                        // playing audio over you.
                        // i.e. for notifications or navigation directions
                        // Depending on your audio playback, you may prefer to
                        // pause playback here instead. You do you.
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                    }
                }
            };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(NumbersActivity.this, "Song is finished", Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> numwords = new ArrayList<Word>();
        numwords.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        numwords.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        numwords.add(new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numwords.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numwords.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        numwords.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numwords.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numwords.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numwords.add(new Word("Nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        numwords.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        /*
        Log.v("NumbersActivity","Word at index"+numwords.get(0));
        LinearLayout rootView = (LinearLayout)findViewById(R.id.list);*/
        //int index =0;
        /*while(index<numwords.size())
        {
            TextView wordView = new TextView(this);
            wordView.setText(numwords.get(index++));
            rootView.addView(wordView);
        }*/
        WordAdapter itemsAdapter = new WordAdapter(this, numwords, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);
        /*GridView gridView = (GridView) findViewById(R.id.list);*/

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = numwords.get(position);
                releaseMediaPlayer();
                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);


                }


            }
        });

        /*gridView.setAdapter(itemsAdapter);*/


    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}


