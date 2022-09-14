package cn.fengyu.class08project;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.FileNotFoundException;

/**
 * 1、快了
 * 2、慢了
 * 3、合适
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    private ImageView imageView;

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Log.v("MainActivity", "Verbose");
        // //无关紧要的信息
        // Log.d("MainActivity", "Debug");
        // //调试信息
        // Log.i("MainActivity", "Info");
        // //普通信息
        // Log.w("MainActivity", "Warning");
        // //警告信息
        // Log.e("MainActivity", "Error");
        // //错误信息
        // // Build打包操作
        // // Java -> Jar包
        // // Android -> Apk包
        // System.out.println("println1");
        // Log.i("System.out", "println2");

        // // 2、声明
        // textView = findViewById(R.id.textView4);
        //
        // // Student s1 = new Student();
        // // s1.say();
        //
        // String content1 = textView.getText().toString();
        // System.out.println(content1);
        // // 3、使用
        // // 方法1：设置控件显示的文本内容
        // textView.setText("你好111");
        // // 方法2：获取控件内容
        // String content2 = textView.getText().toString();
        // System.out.println(content2);
        // //         getText
        // // setTextSize
        // //         setTextColor
        // // 方法3：设置字体大小
        // textView.setTextSize(40);
        // // 方法4：设置文字颜色
        // textView.setTextColor(Color.rgb(255,0,0));

        button = findViewById(R.id.login);
        button.setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        // // 使用匿名内部类方法
        // button.setOnClickListener(view -> {
        //     TextView result = findViewById(R.id.result);
        //     EditText email = findViewById(R.id.email_edit_text);
        //     EditText password = findViewById(R.id.password_edit_text);
        //     // 登录验证
        //     if (email.getText().toString().equals("admin@qq.com") && password.getText().toString().equals("123456")) {
        //         result.setText("登录成功");
        //         result.setTextColor(Color.GREEN);
        //     } else {
        //         result.setText("邮箱或密码错误");
        //         result.setTextColor(Color.RED);
        //     }
        // });

        imageView = findViewById(R.id.imageView);
        // datePicker = findViewById(R.id.dp1);
        // datePicker.init(2022, 8, 6, new DatePicker.OnDateChangedListener() {
        //     @Override
        //     public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        //         System.out.println("year: " + year + ", month: " + month + ", day：" + day);
        //         System.out.println("year: " + datePicker.getYear() +
        //                 ", month: " + datePicker.getMonth() +
        //                 ", day：" + datePicker.getDayOfMonth());
        //     }
        // });
    }

    @Override
    public void onClick(View view) {
        System.out.println("clink……" + view.getId());
        TextView result = findViewById(R.id.result);
        // 显式Intent跳转
        // 1、声明
        Intent intent = new Intent(LoginActivity.this, LoginResultActivity.class);
        switch (view.getId()) {
            case R.id.login:
                EditText email = findViewById(R.id.email_edit_text);
                EditText password = findViewById(R.id.password_edit_text);
                // 登录验证
                if (email.getText().toString().equals("admin@qq.com") && "123456".equals(password.getText().toString())) {
                    Toast toast = Toast.makeText(LoginActivity.this, "测试", Toast.LENGTH_LONG);
                    toast.show();
                    result.setText("登录成功");
                    result.setTextColor(Color.GREEN);
                    // 2、传递数据
                    intent.putExtra("flag", "success");
                    // 3、启动意图，完成跳转
                    startActivity(intent);
                } else {
                    // 2、传递数据
                    intent.putExtra("flag", "failure");
                    // 3、启动意图，完成跳转
                    startActivity(intent);
                }
                System.out.println("登录按钮点击了");
                break;
            case R.id.register:
                // 访问网页
                // Intent intent1 = new Intent(Intent.ACTION_VIEW);
                // intent1.setData(Uri.parse("https://www.baidu.com"));
                // startActivity(intent1);
                // 打电话
                // Intent intent1 = new Intent(Intent.ACTION_DIAL);
                // intent1.setData(Uri.parse("tel:10086"));
                // startActivity(intent1);
                // 获取图片
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 100001);
                break;
            default:
                System.out.println("一个未知的按钮点击了");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.i("Result", "requestCode: " + requestCode);
            Log.i("Result", "data: " + Uri.parse(data.getData().getPath()));
            //使用content的接口
            ContentResolver cr = this.getContentResolver();
            try {
                //获取图片
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(data.getData()));
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.i("Result", "未获取数据");
        }
    }
}