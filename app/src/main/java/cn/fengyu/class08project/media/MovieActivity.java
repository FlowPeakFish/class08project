package cn.fengyu.class08project.media;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.fengyu.class08project.R;
import cn.fengyu.class08project.entity.Music;
import cn.fengyu.class08project.entity.Video;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        videoView = findViewById(R.id.video);

        List<Video> videoList = new ArrayList<>();
        videoList.add(new Video("chacha", "/storage/emulated/0/Movies/chacha.mp4"));
        videoList.add(new Video("latinrumba", "/storage/emulated/0/Movies/latinrumba.mp4"));
        videoList.add(new Video("宿舍大爷心理剧片头", "/storage/emulated/0/Movies/宿舍大爷心理剧片头.mp4"));
        videoList.add(new Video("宿舍大爷心理剧片尾", "/storage/emulated/0/Movies/宿舍大爷心理剧片尾.mp4"));
        videoList.add(new Video("罗密欧与朱丽叶", "/storage/emulated/0/Movies/罗密欧与朱丽叶.mp4"));
        videoList.add(new Video("送孟浩然之广陵", "/storage/emulated/0/Movies/送孟浩然之广陵.mp4"));

        Cursor cursor = getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        List<Video> videoList1 = new ArrayList<>();
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            String name = cursor.getString(nameIndex);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            String artist = cursor.getString(artistIndex);
            videoList1.add(new Video(name, artist));
        }

        // 方式一：外部存储空间
        // /storage/emulated/0
        // videoView.setVideoPath(Environment.getExternalStorageDirectory().getPath() +
        //         "/Movies/罗密欧与朱丽叶.mp4");

        // 方式二：应用内存储空间（占用大量大存储空间）
        // videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.latinrumba));
        // videoView.start();

        // 方式三：线上资源
        videoView.setVideoURI(Uri.parse("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4"));
        MediaController mediaController = new MediaController(MovieActivity.this);
        videoView.setTooltipText("紧急救援宣传片");
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}