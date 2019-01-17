package com.dreadnought.mysharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String SHARE_PREF = "MY_PREF";
    private static final String VOL_MUSIC = "VOLUME_MUSIC";
    private static final String VOL_EFFECT = "VOLUME_EFFECT";
    private TextView volumeMusic, volumeEffect;
    private SeekBar seekBarMusic, seekBarEffect;
    private Button buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumeMusic = findViewById(R.id.volumeMusic);
        volumeEffect = findViewById(R.id.volumeEffect);
        seekBarMusic = findViewById(R.id.seekBarMusic);
        seekBarMusic.setOnSeekBarChangeListener(this);
        seekBarEffect = findViewById(R.id.seekBarEffect);
        seekBarEffect.setOnSeekBarChangeListener(this);
        buttonSave = findViewById(R.id.button);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //my sharedpreferences is here.
                SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(VOL_MUSIC,seekBarMusic.getProgress());
                editor.putInt(VOL_EFFECT,seekBarEffect.getProgress());
                editor.apply();
            }
        });
        loadView();
    }

    private void loadView(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREF,MODE_PRIVATE);
        int progressMusicInit = sharedPreferences.getInt(VOL_MUSIC,50);
        int progressEffectInit = sharedPreferences.getInt(VOL_EFFECT,50);
        seekBarMusic.setProgress(progressMusicInit);
        volumeMusic.setText(progressMusicInit+"%");
        seekBarEffect.setProgress(progressEffectInit);
        volumeEffect.setText(progressEffectInit+"%");
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekBarMusic:
                volumeMusic.setText(progress + "%");
                break;
            case R.id.seekBarEffect:
                volumeEffect.setText(progress + "%");
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //nothing for here.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //nothing for there.
    }
}
