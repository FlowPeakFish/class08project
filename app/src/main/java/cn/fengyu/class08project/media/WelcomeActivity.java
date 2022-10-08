package cn.fengyu.class08project.media;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.fengyu.class08project.R;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    // 1 定义
    TextView textView;
    Timer timer;
    TimerTask timerTask;

    int count = 5;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 2 声明(绑定)
        textView = findViewById(R.id.welcome_num);

        System.out.println(LocalDateTime.now());

        SharedPreferences preferences = getSharedPreferences("status", 0);
        boolean firstLogin = preferences.getBoolean("firstLogin", true);

        if (firstLogin) {
            handler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0x01:
                            count--;
                            textView.setText(Integer.toString(count));
                            if (count == 0) {
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putBoolean("firstLogin", false);
                                editor.commit();

                                Intent intent = new Intent(WelcomeActivity.this, MusicMainActivity.class);
                                startActivity(intent);
                                timer.cancel();
                                timerTask.cancel();
                            }
                            break;
                        case 0x02:
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
                    System.out.println("task运行一次" + LocalDateTime.now());
                }
            };
            // 开启定时器
            timer.schedule(timerTask, 0, 1000);
        } else {
            Intent intent = new Intent(WelcomeActivity.this, MusicMainActivity.class);
            startActivity(intent);
        }
    }
}