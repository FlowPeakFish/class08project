package cn.fengyu.class08project.media;

import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.fengyu.class08project.R;

public class MusicPlayActivity extends AppCompatActivity {

    // 1、定义
    private TextView name, artist, current, end;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        // 2、绑定
        name = findViewById(R.id.name);
        artist = findViewById(R.id.artist);
        current = findViewById(R.id.current);
        end = findViewById(R.id.end);
        seekBar = findViewById(R.id.seekBar);

        int position = getIntent().getIntExtra("position", 0);
        System.out.println(position);
    }
}