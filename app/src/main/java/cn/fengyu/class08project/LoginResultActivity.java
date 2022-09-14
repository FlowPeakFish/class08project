package cn.fengyu.class08project;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class LoginResultActivity extends AppCompatActivity {
    /**
     * 9月6日
     * 1、TextView
     * 2、EditText
     * 3、Button
     * 4、ImageView
     * 5、DatePicker
     * 6、显式Intent
     * 7、隐式Intent
     */

    private ImageView imageView;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d03layout_frame_layout);
        // String flag = getIntent().getStringExtra("flag");
        // System.out.println(flag);
        //
        // imageView = findViewById(R.id.imageView2);
        // textView = findViewById(R.id.textView);
        //
        // if (Objects.equals(flag, "success")) {
        //     System.out.println("登陆成功");
        //     // 修改图片
        //     imageView.setImageResource(R.drawable.login_success);
        //     // 修改文字
        //     textView.setText("恭喜您，登录成功！");
        //     textView.setTextColor(Color.GREEN);
        // } else if (Objects.equals(flag, "failure")) {
        //     System.out.println("登陆失败");
        //     // 修改图片
        //     imageView.setImageResource(R.drawable.login_failure);
        //     // 修改文字
        //     textView.setText("登陆失败！用户名或密码错误。");
        //     textView.setTextColor(Color.RED);
        // } else {
        //     Log.w("Result", "未知Flag");
        // }
    }
}