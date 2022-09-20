package cn.fengyu.class08project;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;

/**
 * 5.2 文件存储
 * Ctrl + Alt + M
 */
public class FileActivity extends AppCompatActivity {

    private EditText content;

    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        /**
         * Android 6.0 要求危险权限必须申请动态权限
         * Android 11 要求通过将用户引导至一个系统设置页面向用户请求“所有文件访问权限”
         */
        if (Environment.isExternalStorageManager()) {
            System.out.println("已获得权限ExternalStorage");
        } else {
            System.out.println("未获得权限ExternalStorage");
            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            startActivity(intent);
        }

        /**
         * 开发23api版本一下的应用时，用来区分
         */
        if (Build.VERSION.SDK_INT > 23) {
            if (
                    PackageManager.PERMISSION_DENIED ==
                            checkSelfPermission(Manifest.permission.CALL_PHONE)) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        10001);
            }
            ;
        }

        // 绑定
        initBindingView();
        // 读取数据
        readContent();
        // 设置监听器
        initListener();
        // 外部存储路径
        Environment.getExternalStorageDirectory();
        // 外部缓存目录
        getExternalCacheDir();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull @NotNull String[] permissions,
                                           @NonNull @NotNull int[] grantResults) {
        System.out.println("============");
        System.out.println("requestCode：" + requestCode);
        System.out.println("permissions：" + Arrays.toString(permissions));
        System.out.println("grantResults：" + Arrays.toString(grantResults));
        System.out.println("============");
        if (grantResults[0] == -1){
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeContent();
            }
        });
    }

    private void writeContent() {
        // 输出文件
        try {
            // 内部存储 (/data/data/<package name>/files)
            // FileOutputStream fos = openFileOutput("content.txt", 0);
            // fos.write(content.getText().toString().getBytes());
            // fos.close();
            // Toast.makeText(FileActivity.this,
            //         "日志已保存", Toast.LENGTH_SHORT).show();

            // 外部存储 (/sdcard) (/storage/emulated/0)
            String path =
                    Environment.getExternalStorageDirectory() + "/" +
                            "content.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getText().toString().getBytes());
            fos.close();
            Toast.makeText(FileActivity.this,
                    "日志已保存", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("FileActivity", "文件输出错误");
            throw new RuntimeException(e);
        }
    }

    private void readContent() {
        try {
            // 内部存储
            // FileInputStream fis = openFileInput("content.txt");
            // byte[] arr = new byte[fis.available()];
            // fis.read(arr);
            // content.setText(new String(arr));
            // fis.close();


            // 外部存储
            String path =
                    Environment.getExternalStorageDirectory() + "/" +
                            "content.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] arr = new byte[fis.available()];
            fis.read(arr);
            content.setText(new String(arr));
            fis.close();
        } catch (IOException e) {
            Log.e("FileActivity", "文件输入错误");
            throw new RuntimeException(e);
        }
    }

    private void initBindingView() {
        content = findViewById(R.id.content);
        save = findViewById(R.id.save);
    }
}