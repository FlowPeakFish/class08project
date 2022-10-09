package cn.fengyu.class08project.media;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.fengyu.class08project.R;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayActivity extends AppCompatActivity {

    // 1、定义
    private TextView name, artist, current, end;
    private SeekBar seekBar;
    MediaPlayer player;

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler;

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
        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        cursor.moveToPosition(position);
        int nameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        String nameStr = cursor.getString(nameIndex);
        int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        String artistStr = cursor.getString(artistIndex);
        name.setText(nameStr);
        artist.setText(artistStr);

        // 1、首先创建MediaPlayer对象；
        player = new MediaPlayer();
        // 2、然后调用setDataSource()方法来设置音频文件的路径；
        int pathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        String path = cursor.getString(pathIndex);
        try {
            // 方式一：存储空间中的媒体资源
            // 方式二：res中的媒体资源
            // 方式三：线上资源
            player.setDataSource(path);
            // 3、再调用prepare()方法使MediaPlayer进入到准备状态；
            player.prepare();
            // 4、调用start方法就可以播放音频。
            player.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0x01:
                        refreshSeekBar();
                        break;
                    default:
                        break;
                }
            }
        };
        // 创建定时器
        timer = new Timer();
        // 执行的任务
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x01);
            }
        };
        // 开启定时器
        timer.schedule(timerTask, 0, 1000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(fromUser + "  :  " + progress);
                if (fromUser) {
                    player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void refreshSeekBar() {
        seekBar.setProgress(player.getCurrentPosition());
        seekBar.setMax(player.getDuration());
        current.setText(milliseconds2Str(player.getCurrentPosition()));
        end.setText(milliseconds2Str(player.getDuration()));
    }

    private String milliseconds2Str(int ms) {
        int totalS = ms / 1000;
        int s = totalS % 60;
        int m = totalS / 60;
        return String.format("%d:%02d", m, s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}