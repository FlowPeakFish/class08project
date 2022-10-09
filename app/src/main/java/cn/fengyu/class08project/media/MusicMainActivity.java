package cn.fengyu.class08project.media;

import android.database.Cursor;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.fengyu.class08project.R;
import cn.fengyu.class08project.adapter.MusicRecyclerAdapter;
import cn.fengyu.class08project.entity.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);

        recyclerView = findViewById(R.id.music_list);

        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        Toast.makeText(MusicMainActivity.this,
                "查询了" + cursor.getCount() + "首音乐", Toast.LENGTH_LONG).show();

        List<Music> musicList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            String name = cursor.getString(nameIndex);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            String artist = cursor.getString(artistIndex);
            musicList.add(new Music(name,artist));
        }

        MusicRecyclerAdapter adapter = new MusicRecyclerAdapter(musicList);
        LinearLayoutManager manager = new LinearLayoutManager(MusicMainActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}